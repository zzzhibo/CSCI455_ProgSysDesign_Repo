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

int main()
{
    cout << "Enter numbers: ";
    vector<int> v = readVals();
    cout << endl;

    printVals(v);

    vector<int> vFiltered = valsGT0(v);

    printVals(vFiltered);

    return 0;
}
