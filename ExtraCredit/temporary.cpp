/*  Name: Zhibo Wang
 *  USC NetID: zhibowan
 *  CS 455 Fall 2019
 *
 *  See ecListFuncs.h for specification of each function.
 */

#include <cstdlib>
#include <cassert>
#include <iostream>

#include "ecListFuncs.h"

using namespace std;

void longestRun(ListType list, int &maxRunVal, int &maxRunLen)
{
    ListType pt1 = list;    // pointer copy
    int valTmp = pt1->data; // temporary variable for maxRunVal
    int lenTmp1 = 1;        // temporary variable for maxRunLen

    if (pt1->next != NULL)
    {
        ListType pt2 = pt1->next;
        int lenTmp2 = 1;
        while (pt2 != NULL)
        {
            // cout << "---------" << endl;
            // cout << "pt1" << pt1->data << endl;
            // cout << "pt2" << pt2->data << endl;

            if (pt2->data == pt1->data)
            {
                lenTmp2++;
                // cout << "lenTmp2 " << lenTmp2 << endl;
            }
            else
            {
                if (lenTmp2 > lenTmp1)
                {
                    lenTmp1 = lenTmp2;
                    // cout << "lenTmp1 " << lenTmp1 << endl;
                    valTmp = pt1->data;
                }
                lenTmp2 = 1; // re-initialize lenTmp2
            }

            // for special case like example 5
            if (pt2->next == NULL && lenTmp2 > lenTmp1)
            {
                lenTmp1 = lenTmp2;
            }

            pt1 = pt2;
            pt2 = pt2->next;
        }
    }

    maxRunVal = valTmp;
    maxRunLen = lenTmp1;
}

void removeMultiplesOf3(ListType &list)
{
    ListType pt1 = list;
    ListType pt2 = NULL;

    while (pt1 != NULL)
    {
        if (pt1->data % 3 == 0)
        {
            if (pt2 == NULL)
            {
                list = list->next;
                delete pt1;
                pt1 = list;
            }
            else
            {
                pt2->next = pt1->next;
                ListType nodeRemove = pt1;
                pt1 = pt1->next;
                delete nodeRemove;
            }
        }
        else
        {
            pt2 = pt1;
            pt1 = pt1->next;
        }
    }
}

void insertMiddle(ListType &list, int midVal)
{
    ListType pt1 = list;
    int counter = 0;

    while (pt1 != NULL)
    {
        counter++;
        pt1 = pt1->next;
    }

    // cout << "counter: " << counter << endl;

    if (counter == 0)
    {
        list = new Node(midVal);
    }
    else if (counter == 1)
    {
        list = new Node(midVal, list);
    }
    else
    {
        pt1 = list; // re-initialize pointer 1
        int midPos = counter / 2;
        // cout << "midPos: " << midPos << endl;
        int pos = 1;

        while (pos != midPos)
        {
            pos++;
            // cout << "pos: " << pos << endl;
            pt1 = pt1->next;
        }

        ListType pt2 = new Node(midVal, pt1->next);
        pt1->next = pt2;
    }
}

ListType merge(ListType list1, ListType list2)
{
    ListType mergedList = NULL;

    if (list1 == NULL)
    {
        return list2;
    }
    else if (list2 == NULL)
    {
        return list1;
    }

    if (list1->data < list2->data) // l1 < l2
    {
        mergedList = list1;
        mergedList->next = merge(list1->next, list2);
    }
    else if (list1->data > list2->data) // l1 > l2
    {
        mergedList = list2;
        mergedList->next = merge(list1, list2->next);
    }
    else // l1 = l2
    {
        mergedList = list1;
        mergedList->next = merge(list1->next, list2->next);
    }

    return mergedList;
}
