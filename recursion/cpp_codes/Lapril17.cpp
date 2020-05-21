#include <iostream>
#include <vector>
#include <unordered_set>
using namespace std;

// java mein hashset ka use krenge
// q139
unordered_set<string> map;
int wordBreak_(string str, string an)
{
    if (str.length() == 0)
    {
        cout << an << endl;
        return 1;
    }
    int count = 0;
    for (int i = 1; i <= str.length(); i++)
    {
        string smallans = str.substr(0, i); //i length hota hai c++ mein
        if (map.find(smallans) != map.end())
        {
            count += wordBreak_(str.substr(i), an + smallans + " ");
        }
    }
    return count;
}

void wordBreak()
{
    vector<string> words = {"i", "like", "ilike", "football", "foot", "ball"};
    for (string word : words)
    {
        map.insert(word);
    }
    cout << wordBreak_("ilikefootball", "");
}
vector<int> mapping = {5, 3, 8, 9};
int stringToNumber(string str)
{
    int num = 0;
    for (int i = 0; i < str.length(); i++)
    {
        num = num * 10 + mapping[str[i] - 'a'];
    }
    return num;
}

string normalStr(string a, string b, string c)
{
    string temp = a + b + c;
    int ans = 0;
    for (int i = 0; i < temp.length(); i++)
    {
        int mask = 1 << temp[i] - 'a';
        ans |= mask;
    }
    temp = "";
    for (int i = 0; i < 26; i++)
    {
        int mask = 1 << i;
        if ((ans & mask) != 0)
        {
            temp = temp + char('a' + i);
        }
    }
    return temp;
}
int numused = 0;
int crypto(string str, int idx, string a, string b, string c)
{

    if (idx == str.length())
    {
        if ((stringToNumber(a) + stringToNumber(b) == stringToNumber(c)) && mapping[a[0] - 'a'] != 0 && mapping[b[0] - 'a'] != 0 && mapping[c[0] - 'a'] != 0)
            return 1;
        return 0;
    }
    int count = 0;
    for (int num = 0; num < 10; num++)
    {
        int mask = 1 << num;
        if ((numused & mask) == 0)
        {
            numused ^= mask;
            mapping[str[idx] - 'a'] = num;
            count += crypto(str, idx + 1, a, b, c);
            mapping[str[idx] - 'a'] = 0;
            numused ^= mask;
        }
    }
    return count;
}


// crossword==============
vector<vector<char>> board{{'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '-', '-', '-', '-', '-', '-', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '+', '+', '+', '+', '+'},
                           {'+', '-', '-', '-', '-', '-', '-', '+', '+', '+'},
                           {'+', '-', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '-', '+', '+', '+', '+'},
                           {'+', '+', '+', '+', '+', '+', '+', '+', '+', '+'}};
vector<string> words = {"agra", "norway", "england", "gwalior"};

bool canPlaceWordH(int r, int c, string &word)
{
    if (c == 0 && word.length() < board[0].size())
    {
        if (board[r][c + word.length()] != '+')
            return false;
    }
    else if ((c + word.length()) == board[0].size() && word.length() != board[0].size())
    {
        if (board[r][c - 1] != '+')
            return false;
    }
    else
    {
        if (((c - 1) >= 0 && board[r][c - 1] != '+') || ((c + word.length()) < board[0].size() && board[r][c + word.length()] != '+'))
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if ((c + i) < board[0].size() && board[r][c + i] != '-' && board[r][c + i] != word[i])
        {
            return false;
        }
    }
    return true;
}

vector<bool> placeWordH(int r, int c, string &word)
{
    vector<bool> mark(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[r][c + i] == '-')
        {
            mark[i] = true;
            board[r][c + i] = word[i];
        }
    }
    return mark;
}

void unPlaceWordH(int r, int c, string &word, vector<bool> &mark)
{
    for (int i = 0; i < word.length(); i++)
    {
        if (mark[i])
            board[r][c + i] = '-';
    }
}

bool canPlaceWordV(int r, int c, string &word)
{
    if (r == 0 && r + word.length() < board.size())
    {
        if (board[r + word.length()][c] != '+')
            return false;
    }
    else if ((r + word.length()) == board.size() && word.length() != board.size())
    {
        if (board[r - 1][c] != '+')
            return false;
    }
    else
    {
        if (((r - 1) >= 0 && board[r - 1][c] != '+') || ((r + word.length()) < board.size() && board[r + word.length()][c] != '+'))
            return false;
    }

    for (int i = 0; i < word.length(); i++)
    {
        if ((r + i) < board.size() && board[r + i][c] != '-' && board[r + i][c] != word[i])
        {
            return false;
        }
    }
    return true;
}

vector<bool> placeWordV(int r, int c, string &word)
{
    vector<bool> mark(word.length(), false);
    for (int i = 0; i < word.length(); i++)
    {
        if (board[r + i][c] == '-')
        {
            mark[i] = true;
            board[r + i][c] = word[i];
        }
    }
    return mark;
}

void unPlaceWordV(int r, int c, string &word, vector<bool> &mark)
{
    for (int i = 0; i < word.length(); i++)
    {
        if (mark[i])
            board[r + i][c] = '-';
    }
}

bool crossWord_(int idx)
{
    if (idx == words.size())
    {
        for (vector<char> &ar : board)
        {
            for (char ch : ar)
                cout << ch << " ";
            cout << endl;
        }
        return true;
    }

    string word = words[idx];
    bool res = false;
    for (int i = 0; i < board.size(); i++)
    {
        for (int j = 0; j < board[0].size(); j++)
        {
            if (board[i][j] == '-' || board[i][j] == word[0])
            {
                if (canPlaceWordH(i, j, word))
                {
                    vector<bool> mark = placeWordH(i, j, word);
                    res = res || crossWord_(idx + 1);
                    unPlaceWordH(i, j, word, mark);
                }

                if (canPlaceWordV(i, j, word))
                {
                    vector<bool> mark = placeWordV(i, j, word);
                    res = res || crossWord_(idx + 1);
                    unPlaceWordV(i, j, word, mark);
                }
            }
        }
    }

    return res;
}

void crossWord()
{
    cout << crossWord_(0) << endl;
}
int main()
{
    // cout << crypto(normalStr("send", "more", "money"), 0, "send", "more", "money");
    // wordBreak();
    crossWord();
}