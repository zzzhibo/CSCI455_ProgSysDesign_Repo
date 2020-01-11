// Name: Zhibo Wang
// USC NetID: zhibowan
// CSCI 455 PA5
// Fall 2019

// Table.cpp  Table class implementation

#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"

//*************************************************************************

Table::Table()
{
   hashSize = HASH_SIZE;
   linkedChains = new ListType[HASH_SIZE]();
   numNodes = 0;
}

Table::Table(unsigned int hSize)
{
   hashSize = hSize;
   linkedChains = new ListType[hSize]();
   numNodes = 0;
}

int *Table::lookup(const string &key)
{
   unsigned int hash = hashCode(key);

   if (containNode(linkedChains[hash], key))
   {
      ListType pt = linkedChains[hash];

      while (pt->key != key)
      {
         pt = pt->next;
      }

      return &(pt->value); // return the address of key's value if existing
   }

   return NULL;
}

bool Table::remove(const string &key)
{
   unsigned int hash = hashCode(key);

   if (removeNode(linkedChains[hash], key))
   {
      numNodes--;
      return true;
   }

   return false; // dummy return value for stub
}

bool Table::insert(const string &key, int value)
{
   unsigned int hash = hashCode(key);

   if (addNode(linkedChains[hash], key, value))
   {
      numNodes++;
      return true;
   }

   return false;
}

int Table::numEntries() const
{
   return numNodes; // return number of total entries
}

void Table::printAll() const
{
   for (int i = 0; i < hashSize; i++)
   {
      if (linkedChains[i] != NULL)
      {
         printNodes(linkedChains[i]);
      }
   }
}

void Table::hashStats(ostream &out) const
{
   out << "number of buckets: " << hashSize << endl;
   out << "number of entries: " << numNodes << endl;
   out << "number of non-empty buckets: " << nonEmpty(linkedChains) << endl;
   out << "longest chain: " << longestChain(linkedChains) << endl;
}

// add definitions for your private methods here

int Table::longestChain(ListType *in) const
{
   int lengthLL = 0; // length of linked chain
   int size = 0;     // size variable for each chain

   for (int i = 0; i < hashSize; i++)
   {
      size = getSize(in[i]);
      (size > lengthLL) ? lengthLL = size : lengthLL = lengthLL; // use ternary expression to update the largest number
   }

   return lengthLL;
}

int Table::nonEmpty(ListType *in) const
{
   int nonEmpty = 0;

   for (int i = 0; i < hashSize; i++)
   {
      if (in[i] != NULL)
      {
         nonEmpty++;
      }
   }

   return nonEmpty;
}