#include <iostream>
#include <vector>
using namespace std;

// ===============================================queenset===========================================================

int queencobination1D(vector<bool> boxes, int qpsf, int tq, int idx, string ans)
{
    if (qpsf == tq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = idx; i < boxes.size(); i++)
    {
        count += queencobination1D(boxes, qpsf + 1, tq, idx + 1, ans + "B" + to_string(i) + "Q" + to_string(qpsf));
    }
    return count;
}
int queenpermutation1D(vector<bool> boxes, int qpsf, int tq, int idx, string ans)
{
    if (qpsf == tq)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < boxes.size(); i++)
    {
        if (!boxes[i])
        {
            boxes[i] = true;
            count += queenpermutation1D(boxes, qpsf + 1, tq, idx + 1, ans + "B" + to_string(i) + "Q" + to_string(qpsf));
            boxes[i] = false;
        }
    }
    return count;
}

int queenpermutation1D_sub(vector<bool> boxes, int qpsf, int tq, int idx, string ans)
{
    if (qpsf == tq || idx == boxes.size())
    {
        if (idx == boxes.size())
            return 0;

        cout << ans << endl;
        return 1;
    }

    int count = 0;

    if (!boxes[idx])
    {
        boxes[idx] = true;
        count += queenpermutation1D_sub(boxes, qpsf + 1, tq, 0, ans + "B" + to_string(idx) + "Q" + to_string(qpsf));
        boxes[idx] = false;
    }
    count += queenpermutation1D_sub(boxes, qpsf, tq, idx + 1, ans);
}

int combinationQueen2D(vector<vector<bool>> &boxes, int qpsf, int tnq, int idx, string ans)
{ //qpsf : queen place so far, tnq: total no of queen
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < boxes.size() * boxes[0].size(); i++)
    {
        int x = i / boxes[0].size();
        int y = i % boxes[0].size();
        count += combinationQueen2D(boxes, qpsf + 1, tnq, i + 1, ans + "(" + to_string(x) + ", " + to_string(y) + ") ");
    }
    return count;
}

int permutationQueen2D(vector<vector<bool>> &boxes, int qpsf, int tnq, string ans) // boolean[][]
{                                                                                  //qpsf : queen place so far, tnq: total no of queen
    if (qpsf == tnq)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = 0; i < boxes.size() * boxes[0].size(); i++)
    {
        int x = i / boxes[0].size();
        int y = i % boxes[0].size();
        if (!boxes[x][y])
        {
            boxes[x][y] = true;
            count += permutationQueen2D(boxes, qpsf + 1, tnq, ans + "(" + to_string(x) + ", " + to_string(y) + ") ");
            boxes[x][y] = false;
        }
    }
    return count;
}

bool isSafeToPlaceQueen(vector<vector<bool>> &boxes, int row, int col)
{
    vector<vector<int>> Qdir = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

    for (int d = 0; d < Qdir.size(); d++)
    {
        for (int rad = 1; rad <= boxes.size(); rad++)
        {
            int r = row + rad * Qdir[d][0];
            int c = col + rad * Qdir[d][1];

            if (r >= 0 && c >= 0 && r < boxes.size() && c < boxes.size())
            {
                if (boxes[r][c])
                    return false;
            }
            else
                break;
        }
    }

    return true;
}

int Nqueen_01(vector<vector<bool>> &boxes, int tnq, int idx, string ans)
{ //qpsf : queen place so far, tnq: total no of queen
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }

    int count = 0;
    for (int i = idx; i < boxes.size() * boxes[0].size(); i++)
    {
        int x = i / boxes[0].size();
        int y = i % boxes[0].size();
        if (isSafeToPlaceQueen(boxes, x, y))
        {
            boxes[x][y] = true;
            count += Nqueen_01(boxes, tnq - 1, i + 1, ans + "(" + to_string(x) + ", " + to_string(y) + ") ");
            boxes[x][y] = false;
        }
    }
    return count;
}

int main()
{
    vector<bool> arr(6, false);
    cout << queenpermutation1D_sub(arr, 0, 3, 0, "");
    return 0;
}
