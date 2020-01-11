// Name: Zhibo Wang
// USC NetID: zhibowan
// CSCI 455 PA5
// Fall 2019

#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue)
{
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n)
{
   key = theKey;
   value = theValue;
   next = n;
}

//*************************************************************************
// put the function definitions for your list functions below

// returns the size of the list
int getSize(const ListType &list)
{
   int listSize = 0;
   ListType pt = list; // local pointer to the list

   while (pt != NULL)
   {
      listSize++;
      pt = pt->next;
   }

   return listSize;
}

// check if the node with given key is in the list.
bool containNode(const ListType &list, string theKey)
{
   ListType pt = list;

   while (pt != NULL)
   {
      if (pt->key == theKey)
      {
         return true;
      }
      pt = pt->next;
   }
   return false;
}

// add the node to the list with the given key and the given value, return false if the node is already in the list.
bool addNode(ListType &list, string theKey, int theValue)
{
   if (containNode(list, theKey) == true)
   {
      return false; // can't add an existing node
   }
   else
   {
      list = new Node(theKey, theValue, list);
      return true;
   }
}

// remove the node from the list with the given key. If the node doesn't exist return false.
bool removeNode(ListType &list, string theKey)
{
   ListType pt = list;
   ListType ptTmp = NULL; // temporary pointer for updating the list

   while (pt != NULL)
   {
      if (pt->key == theKey)
      {
         if (ptTmp == NULL)
         {
            list = list->next; // when the first node gets removed
         }
         else
         {
            ptTmp->next = pt->next; // link the second next node to the current node
            delete pt;              // delete target node to prevent memory leak
         }
         return true; // done with removing and return true
      }
      ptTmp = pt;
      pt = pt->next;
   }

   return false;
}

// update the node with given key by value in the list. If the node doesn't exist return false.
bool updateNode(const ListType &list, string theKey, int theValue)
{
   ListType pt = list;

   while (pt != NULL)
   {
      if (pt->key == theKey)
      {
         pt->value = theValue;
         return true;
      }
      pt = pt->next;
   }
   return false;
}

// print all function which prints every node in the list
void printNodes(const ListType &list)
{
   if (list == NULL)
   {
      cout << "THE LIST IS EMPTY" << endl;
   }

   ListType pt = list;

   while (pt != NULL)
   {
      cout << pt->key << " " << pt->value << endl;

      pt = pt->next;
   }
}
