// Name: Zhibo Wang
// USC NetID: zhibowan
// CSCI 455 PA5
// Fall 2019

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"

void add(ListType &list, string name, int value)
{

   if (addNode(list, name, value))
   {
      cout << "add successfully" << endl;
      printNodes(list);
      cout << "list has " << getSize(list) << endl;
      cout << "------------------------" << endl;
   }
   else
   {
      cout << "add fails" << endl;
      cout << "list has " << getSize(list) << endl;
      cout << "------------------------" << endl;
   }
}

void remove(ListType &list, string name)
{

   if (removeNode(list, name))
   {
      cout << "remove successfully" << endl;
      printNodes(list);
      cout << "list has " << getSize(list) << endl;
      cout << "------------------------" << endl;
   }
   else
   {
      cout << "remove fails" << endl;
      cout << "list has " << getSize(list) << endl;
      cout << "------------------------" << endl;
   }
}

void update(ListType &list, string name, int newValue)
{

   if (updateNode(list, name, newValue))
   {
      cout << "update successfully" << endl;
      printNodes(list);
      cout << "list has " << getSize(list) << endl;
      cout << "------------------------" << endl;
   }
   else
   {
      cout << "update fails" << endl;
      cout << "list has " << getSize(list) << endl;
      cout << "------------------------" << endl;
   }
}

void contain(ListType &list, string name)
{

   if (containNode(list, name))
   {
      cout << "YES it contains " << name << endl;
      printNodes(list);
      cout << "list has " << getSize(list) << endl;
      cout << "------------------------" << endl;
   }
   else
   {
      cout << "NO it does not cotain " << name << endl;
      printNodes(list);
      cout << "list has " << getSize(list) << endl;
      cout << "------------------------" << endl;
   }
}

int main()
{

   ListType thelist = NULL;
   char command;
   int score;
   string name;
   bool handle = true;

   while (handle)
   {

      cout << "(a)dd, (r)emove, (u)pdate, (c)ontain, (q)uit: ";

      cin >> command;

      switch (command)
      {
      case 'a':
         cout << "name: ";
         cin >> name;
         cout << "score: ";
         cin >> score;
         add(thelist, name, score);
         break;
      case 'u':
         cout << "name: ";
         cin >> name;
         cout << "score: ";
         cin >> score;
         update(thelist, name, score);
         break;
      case 'r':
         cout << "name: ";
         cin >> name;
         remove(thelist, name);
         break;
      case 'c':
         cout << "name: ";
         cin >> name;
         contain(thelist, name);
         break;
      case 'q':
         handle = false;
         break;
      default:
         cout << endl;
         break;
      }
   }

   return 0;
}