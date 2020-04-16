#include <iostream>
#include <vector>
using namespace std;

int kthOFFtoON(int num, int k)
{
    int mask = 1 << k;
    return (num | mask);
}

int kthONtoOFF(int num, int k)
{
    int mask = ~(1 << k);
    return (num & mask);
}

int noOfSetBits(int num)
{
    int bitcount = 0;
    int totalBits = 32; //totral bits 32 hoti hai ye infinite loop se bachne ke liye use kr rhe hai
    while (num != 0 && totalBits != 0)
    {
        if ((num & 1) != 0)
            bitcount++;
        num = (num >> 1);
        totalBits--;
    }
    return bitcount;
}

int noOfSetBit_1(int num)
{
    int bitcount = 0;
    while (num != 0)
    {
        num &= (num - 1);
        bitcount++;
    }
    return bitcount;
}

// n-1 can be obtained by flipping all the bits from rightmost 1 including that 1 also
// 0 is false
bool powerOf2(int n)
{
    return n > 0 && (n & (n - 1)) == 0;
}
// ek array mein saare num k times aa rhe hai bs ek num 1 baar aaya hai vhi number chaiye
int singlenumberII(vector<int> &arr, int k)
{
    int count = 0;
    int singlenum = 0;
    for (int i = 0; i < 32; i++)
    {
        int mask = 1 << i;
        for (int ele : arr)
        {
            if ((ele & mask) != 0)
                count++;
        }
        if (count % k != 0)
            singlenum |= mask;
    }
    return singlenum;
}
//leetcode 260
vector<int> singlenumIII(vector<int> &nums)
{
    int xorNum = 0;
    for (int ele : nums)
        xorNum ^= ele;

    int firstLSB = (xorNum & (-xorNum));
    int a = 0;
    int b = 0;
    for (int ele : nums)
    {
        if ((firstLSB & ele) != 0)
            a ^= ele;
        else
            b ^= ele;
    }

    return {a, b}; // java: new int[]{a,b};
}
bool ispowerof4(int n)
{
    if (n > 0 && (n & (n - 1)) == 0)
    {
        int count = 0;
        while (n > 1)
        {
            count++;
            n >>= 1;
        }
        if ((count & 1) == 0)
        {
            return true;
        }
    }
    return false;
}
int main()
{
}