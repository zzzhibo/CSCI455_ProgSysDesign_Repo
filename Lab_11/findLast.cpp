#include <iostream>
#include <vector>

using namespace std;

void printVals(vector<int> v)
{
    for (int i = 0; i < v.size(); i++)
    {
        cout << v[i] << " ";
    }
    cout << endl;
}

vector<int> readVals()
{
    int val;
    vector<int> v;

    while (cin >> val)
    {
        v.push_back(val);
    }

    return v;
}

vector<int> valsGT0(vector<int> v)
{
    vector<int> vFiltered;
    for (int i = 0; i < v.size(); i++)
    {
        if (v[i] > 0)
        {
            vFiltered.push_back(v[i]);
        }
    }

    return vFiltered;
}

int findLast(vector<int> v, int target)
{
    int loc = -1; // -1 by default which is for not found case

    for (int i = 0; i < v.size(); i++)
    {
        if (v[i] == target)
        {
            loc = i;
        }
        else
        {
            continue;
        }
    }

    return loc;
}

void testFindLast()
{
    vector<int> v = readVals();
    int target = 7;
    int loc = findLast(v, target);

    cout << "The last instance of " << target << " is at position " << loc << endl;
}

int main()
{

    testFindLast();

    return 0;
}
