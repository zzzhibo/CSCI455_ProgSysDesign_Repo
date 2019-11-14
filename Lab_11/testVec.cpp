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

int main()
{
    cout << "Enter numbers: ";
    vector<int> v = readVals();
    cout << endl;

    printVals(v);

    return 0;
}
