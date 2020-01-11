/*  Name: Zhibo Wang
 *  USC NetID: zhibowan
 *  CS 455 Fall 2019
 *
 *  See ecListFuncs.h for specification of each function.
 */

// for NULL
#include <cstdlib>

// in case you want to use assert statements
#include <cassert>

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
         if (pt2->data == pt1->data)
         {
            lenTmp2++;
         }
         else
         {
            if (lenTmp2 > lenTmp1)
            {
               lenTmp1 = lenTmp2;
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
      int pos = 1;

      while (pos != midPos)
      {
         pos++;
         pt1 = pt1->next;
      }

      ListType pt2 = new Node(midVal, pt1->next);
      pt1->next = pt2;
   }
}

ListType merge(ListType list1, ListType list2)
{
   ListType mergedList = NULL;

   if (list1 == NULL) // base cases
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
      delete list2;
   }

   return mergedList;
}
