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

int main()
{
    cout << crypto(normalStr("send", "more", "money"), 0, "send", "more", "money");
    // wordBreak();
}