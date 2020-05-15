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
    for (int i = 0; i < Qdir.size(); i++)
    {
        for (int jump = 1; jump <= Qdir.size(); jump++)
        {
            int r = row + jump * Qdir[i][0];
            int c = col + jump * Qdir[i][1];
            if (r >= 0 && r < boxes.size() && c >= 0 && c < boxes[0].size())
            {
                if (boxes[r][c])
                    return false;
            }
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
    for (int i = 0; i < boxes.size() * boxes[0].size(); i++)
    {
        int r = i / boxes[0].size();
        int c = i % boxes[0].size();
        if (isSafeToPlaceQueen(boxes, r, c))
        {
            boxes[r][c] = true;
            count += Nqueen_01(boxes, tnq - 1, idx + 1, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            boxes[r][c] = false;
        }
    }
    return count;
}

vector<bool> rowA;
vector<bool> colA;
vector<bool> diag;
vector<bool> adiag;
int Nqueen_2(int n, int m, int tnq, int idx, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < n * m; i++)
    {
        int r = i / m;
        int c = i % m;
        if (!rowA[r] && !colA[c] && !diag[r - c + m - 1] && !adiag[r + c])
        {
            rowA[r] = true;
            colA[c] = true;
            diag[r - c + m - 1] = true;
            adiag[r + c] = true;
            count += Nqueen_2(n, m, tnq - 1, idx, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            rowA[r] = false;
            colA[c] = false;
            diag[r - c + m - 1] = false;
            adiag[r + c] = false;
        }
    }
    return count;
}

int rowA_ = 0;
int colA_ = 0;
int diag_ = 0;
int adiag_ = 0;
int Nqueen_3(int n, int m, int tnq, int idx, string ans)
{
    if (tnq == 0)
    {
        cout << ans << endl;
        return 1;
    }
    int count = 0;
    for (int i = 0; i < m * n; i++)
    {
        int r = i / m;
        int c = i % m;
        if (!(rowA_ & (1 << r)) && !(colA_ & (1 << c)) && !(diag_ & (1 << (r - c + m - 1))) && !(adiag_ & (1 << (r + c))))
        {
            rowA_ ^= (1 << r);
            colA_ ^= (1 << c);
            diag_ ^= (1 << (r - c + m - 1));
            adiag_ ^= (1 << (r + c));
            count += Nqueen_3(n, m, tnq - 1, idx + 1, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            rowA_ ^= (1 << r);
            colA_ ^= (1 << c);
            diag_ ^= (1 << (r - c + m - 1));
            adiag_ ^= (1 << (r + c));
        }
    }
    return count;
}

int Nqueen_3_sub(int n, int m, int tnq, int idx, string ans)
{
    if (idx == m * n || tnq == 0)
    {
        if (tnq == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;

    int r = idx / m;
    int c = idx % m;
    if (!(rowA_ & (1 << r)) && !(colA_ & (1 << c)) && !(diag_ & (1 << (r - c + m - 1))) && !(adiag_ & (1 << (r + c))))
    {
        rowA_ ^= (1 << r);
        colA_ ^= (1 << c);
        diag_ ^= (1 << (r - c + m - 1));
        adiag_ ^= (1 << (r + c));
        count += Nqueen_3_sub(n, m, tnq - 1, idx + 1, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
        rowA_ ^= (1 << r);
        colA_ ^= (1 << c);
        diag_ ^= (1 << (r - c + m - 1));
        adiag_ ^= (1 << (r + c));
    }
    count += Nqueen_3_sub(n, m, tnq, idx + 1, ans);
    return count;
}

int Nqueen_4(int n, int m, int tnq, int row, string ans)
{
    if (row == n || tnq == 0)
    {
        if (tnq == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    for (int i = 0; i < m; i++)
    {
        int r = row;
        int c = i;
        if (!(rowA_ & (1 << r)) && !(colA_ & (1 << c)) && !(diag_ & (1 << (r - c + m - 1))) && !(adiag_ & (1 << (r + c))))
        {
            rowA_ ^= (1 << r);
            colA_ ^= (1 << c);
            diag_ ^= (1 << (r - c + m - 1));
            adiag_ ^= (1 << (r + c));
            count += Nqueen_4(n, m, tnq - 1, row + 1, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            rowA_ ^= (1 << r);
            colA_ ^= (1 << c);
            diag_ ^= (1 << (r - c + m - 1));
            adiag_ ^= (1 << (r + c));
        }
    }
    return count;
}

int Nqueen_4_sub(int n, int m, int tnq, int row, string ans)
{
    if (row == n || tnq == 0)
    {
        if (tnq == 0)
        {
            cout << ans << endl;
            return 1;
        }
        return 0;
    }
    int count = 0;
    for (int i = 0; i < m; i++)
    {
        int r = row;
        int c = i;
        if (!(rowA_ & (1 << r)) && !(colA_ & (1 << c)) && !(diag_ & (1 << (r - c + m - 1))) && !(adiag_ & (1 << (r + c))))
        {
            rowA_ ^= (1 << r);
            colA_ ^= (1 << c);
            diag_ ^= (1 << (r - c + m - 1));
            adiag_ ^= (1 << (r + c));
            count += Nqueen_4_sub(n, m, tnq - 1, row + 1, ans + "(" + to_string(r) + ", " + to_string(c) + ") ");
            rowA_ ^= (1 << r);
            colA_ ^= (1 << c);
            diag_ ^= (1 << (r - c + m - 1));
            adiag_ ^= (1 << (r + c));
        }
    }
    count += Nqueen_4_sub(n, m, tnq, row + 1, ans);
    return count;
}

int main()
{
    vector<bool> arr(6, false);
    cout << Nqueen_4(3, 3, 3, 0, "");
    return 0;
}
