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

// ====================================floodfill===============================
// 8 direction hai aur hme last tak pauchna ha

vector<vector<int>> dir = {{0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};
vector<string> dirN = {"R", "E", "U", "N", "L", "W", "D", "S"};

bool isPossiblefloodfill(vector<vector<int>> board, int cr, int cc)
{
    if (cr < 0 || cr >= board.size() || cc <= 0 || cc >= board.size() || board[cr][cc] == 2 || board[cr][cc] == 0)
        return false;

    return true;
}

int floodfill(int sr, int sc, int er, int ec, vector<vector<int>> &board, int rad, string ans)
{
    if (sr == er && sc == ec)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    board[sr][sc] = 2;
    for (int i = 0; i < dir.size(); i++)
    {
        for (int mag = 1; mag <= rad; mag++)
        {
            int r = sr + mag * dir[i][0];
            int c = sc + mag * dir[i][1];
            if (isPossiblefloodfill(board, r, c))
            {

                count += floodfill(r, c, er, ec, board, rad, ans + dirN[i] + to_string(mag));
            }
        }
    }
    board[sr][sc] = 1;
    return count;
}




void mazePathSet()
{
    int sr = 0, sc = 0, er = 2, ec = 2;
    // vector<string> ans;

    // ans= mazepath_HV_01(sr, sc, er, ec);
    // ans = mazepath_HVD_01(sr, sc, er, ec);
    // ans = mazepath_multiHVD_01(sr, sc, er, ec);

    // cout << mazepath_HV_02(sr, sc, er, ec, "") << endl;
    // cout << mazepath_HVD_02(sr, sc, er, ec, "") << endl;
    // cout << mazepath_multiHVD_02(sr, sc, er, ec, "") << endl;

    vector<vector<int>> board(er + 1, vector<int>(ec + 1, 1));
    // int rad = max(er, ec);
    int rad = 1;
    cout << floodfill(sr, sc, er, ec, board, rad, "") << endl;

    // for (string s : ans)
    // {
    //     cout << s << " ";
    // }
}
int main()
{
    mazePathSet();

    return 0;
}