#include <iostream>
#include <vector>
using namespace std;

void printIncreasing(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << " ";
    printIncreasing(a + 1, b);
}

void printDecreasing(int a, int b)
{
    if (a == b + 1)
        return;

    printDecreasing(a + 1, b);
    cout << a << " ";
}

void printIncDec(int a, int b)
{
    if (a == b + 1)
        return;

    cout << a << "hi" << endl;
    printIncDec(a + 1, b);
    cout << a << "bye" << endl;
}

void printEvenOdd(int a, int b)
{
    if (a == b + 1)
        return;

    if (a % 2 == 0) //even
        cout << a << " ";
    printEvenOdd(a + 1, b);
    if (a % 2 != 0) //odd
        cout << a << " ";
}

vector<string> permuta_with_dup(string str)
{
    if (str.length() == 1)
    {
        vector<string> base;
        base.push_back(str);
        return base;
    }
    char ch = str[0];
    vector<string> recAns = permuta_with_dup(str.substr(1));
    vector<string> myAns;
    for (string str : recAns)
    {
        for (int i = 0; i < str.length(); i++)
        {
            myAns.push_back(str.substr(0, i) + ch + str.substr(i));
        }
    }
    return myAns;
}

vector<string> words = {
    ":;/",
    "abc",
    "def",
    "ghi",
    "jkl",
    "mno",
    "pqrs",
    "tuv",
    "wxyz",
    "&*%",
    "#@$",
};
// ek input hai string 1234 uski keys pr alphabets se kitne combinations bn skte hai
vector<string> nokiaKeyPad_01(string str)
{
    if (str.length() == 0)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    int idx = str[0] - '0';
    string word = words[idx];

    vector<string> smallAns = nokiaKeyPad_01(str.substr(1));
    vector<string> myAns;

    for (string s : smallAns)
    {
        for (int i = 0; i < word.length(); i++)
        {
            myAns.push_back(word[i] + s);
        }
    }

    return myAns;
}
int nokiaKeyPad_02(string str, string ans)
{
    if (str.length() == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int idx = str[0] - '0';
    string word = words[idx];
    int count = 0;

    for (int i = 0; i < word.length(); i++)
    {
        count += nokiaKeyPad_02(str.substr(1), ans + word[i]);
    }

    if (str.length() > 1)
    {
        int idx1 = str[1] - '0';
        int num = idx * 10 + idx1;
        if (num >= 10 && num <= 11)
        {
            word = words[num];
            for (int i = 0; i < word.length(); i++)
            {
                count += nokiaKeyPad_02(str.substr(2), ans + word[i]);
            }
        }
    }
    return count;
}
int main()
{
    // random();
    return 0;
}