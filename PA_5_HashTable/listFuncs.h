// Name: Zhibo Wang
// USC NetID: zhibowan
// CSCI 455 PA5
// Fall 2019

//*************************************************************************
// Node class definition
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

struct Node
{
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};

typedef Node *ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// returns the size of the list
int getSize(const ListType &list);

// check if the node with given key is in the list.
bool containNode(const ListType &list, std::string theKey);

// add the node to the list with the given key and the given value, return false if the node is already in the list.
bool addNode(ListType &list, std::string theKey, int theValue);

// remove the node from the list with the given key. If the node doesn't exist return false.
bool removeNode(ListType &list, std::string theKey);

// update the node with given key by value in the list. If the node doesn't exist return false.
bool updateNode(const ListType &list, std::string theKey, int theValue);

// print all function which prints every node in the list
void printNodes(const ListType &list);

// keep the following line at the end of the file
#endif
