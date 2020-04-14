#include <iostream>
#include <vector>
using namespace std;

vector<string> mazepath_HV_01(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string> myAns;
    if (sr + 1 <= er)
    {
        vector<string> recAns_H = mazepath_HV_01(sr + 1, sc, er, ec);
        for (string str : recAns_H)
        {
            myAns.push_back("H" + str);
        }
    }
    if (sc + 1 <= ec)
    {
        vector<string> recAns_V = mazepath_HV_01(sr, sc + 1, er, ec);
        for (string str : recAns_V)
        {
            myAns.push_back("V" + str);
        }
    }
    return myAns;
}

vector<string> mazepath_HVD_01(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }
    vector<string> myAns;
    if (sc + 1 <= ec)
    {
        vector<string> Hori = mazepath_HVD_01(sr, sc + 1, er, ec);
        for (string s : Hori)
            myAns.push_back("H" + s);
    }

    if (sc + 1 <= ec && sr + 1 <= er)
    {
        vector<string> Diag = mazepath_HVD_01(sr + 1, sc + 1, er, ec);
        for (string s : Diag)
            myAns.push_back("D" + s);
    }

    if (sr + 1 <= er)
    {
        vector<string> Verti = mazepath_HVD_01(sr + 1, sc, er, ec);
        for (string s : Verti)
            myAns.push_back("V" + s);
    }

    return myAns;
}

vector<string> mazepath_multiHVD_01(int sr, int sc, int er, int ec)
{
    if (sr == er && sc == ec)
    {
        vector<string> base;
        base.push_back("");
        return base;
    }

    vector<string> myAns;
    for (int jump = 1; sc + jump <= ec; jump++)
    {
        vector<string> Hori = mazepath_multiHVD_01(sr, sc + jump, er, ec);
        for (string s : Hori)
            myAns.push_back("H" + to_string(jump) + s);
    }

    for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
    {
        vector<string> Diag = mazepath_multiHVD_01(sr + jump, sc + jump, er, ec);
        for (string s : Diag)
            myAns.push_back("D" + to_string(jump) + s);
    }
    for (int jump = 1; sr + jump <= er; jump++)
    {
        vector<string> Verti = mazepath_multiHVD_01(sr + jump, sc, er, ec);
        for (string s : Verti)
            myAns.push_back("V" + to_string(jump) + s);
    }

    return myAns;
}

int mazepath_HV_02(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    if (sr + 1 <= er)
        count += mazepath_HV_02(sr + 1, sc, er, ec, ans + "H");
    if (sc + 1 <= ec)
        count += mazepath_HV_02(sr, sc + 1, er, ec, ans + "V");
    return count;
}

int mazepath_HVD_02(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    if (sc + 1 <= ec)
        count += mazepath_HVD_02(sr + 0, sc + 1, er, ec, ans + "H");
    if (sc + 1 <= ec && sr + 1 <= er)
        count += mazepath_HVD_02(sr + 1, sc + 1, er, ec, ans + "D");
    if (sr + 1 <= er)
        count += mazepath_HVD_02(sr + 1, sc + 0, er, ec, ans + "V");

    return count;
}

int mazepath_multiHVD_02(int sr, int sc, int er, int ec, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int jump = 1; sc + jump <= ec; jump++)
        count += mazepath_multiHVD_02(sr + jump * 0, sc + jump * 1, er, ec, ans + "H" + to_string(jump));

    for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
        count += mazepath_multiHVD_02(sr + jump * 1, sc + jump * 1, er, ec, ans + "D" + to_string(jump));

    for (int jump = 1; sr + jump <= er; jump++)
        count += mazepath_multiHVD_02(sr + jump, sc, er, ec, ans + "V" + to_string(jump));

    return count;
}



int main()
{

    return 0;
}