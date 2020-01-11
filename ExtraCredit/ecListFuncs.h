// ecListFuncs.h
// CSCI 455 Fall 2019, Extra Credit assignment
// You do not need to modify or submit this file.

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H


//*************************************************************************
// Node type used for lists
struct Node {
   int data;
   Node *next;

   Node(int item);
   Node(int item, Node *n);

};


typedef Node * ListType;

//*************************************************************************
// Utility functions used by the test driver code
//    (these are defined in ectest.cpp)

// makes list into an empty list
//   reclaiming memory used by nodes 
// PRE: list is a well-formed list
// POST: list' is NULL
void clearList(Node * &list);

// prints list elements, space separated, ending with '\n'
// prints empty list as "<empty>"
// PRE: list is a well-formed list
void printList(Node *list);



//*************************************************************************
// Functions you need to write for this assignment:
//   (implementations go in ecListFuncs.cpp)

/*
 * longestRun
 *    A run is several adjacent nodes that have the same value.
 *
 * PRE: list is a well-formed list with at least one node
 *
 * After the function runs:
 *   maxRunVal will contain the value that occurred in the longest run
 *   maxRunLen will contain the length of that run
 * If there are multiple sequences of the max length, it will return the value for the first one
 *    (see Examples 1 and 3 below).
 *                                          maxRunVal   maxRunLen
 *  Example1: list = (3 0 -10)              3           1
 *  Example2: list = (3 7 5 0 0 9)          0           2
 *  Example3: list = (5 5 5 2 2 2 9 9 9)    5           3
 *  Example3: list = (3)                    3           1
 *  Example4: list = (7 7 2 2 2 2 4 4 4)    2           4
 *  Example5: list = (7 7 3 3 7 7 7)        7           3
 */
void longestRun(ListType list, int & maxRunVal, int & maxRunLen);


/*
 * removeMultiplesOf3
 *
 * PRE: list is a well-formed list.
 *
 * removes all all of the multiples of 3 from the list
 *
 * Examples:
 *  list before call  list after call
 *    ()                ()
 *    (6 2 3 3 7 12)    (2 7)
 *    (3 6 9 12)        ()
 *    (1 5 1 7)         (1 5 1 7)
 */
void removeMultiplesOf3(ListType & list);


/*
 * insertMiddle
 *
 * PRE: list is a well-formed list.
 *
 * inserts midVal at the middle of the list
 * If the list has an odd number of values beforehand, it will go just left of the middle.
 *
 * Examples:
 *  list before call  midVal    list after call
 *    (1 1 1 1 1 1)   6         (1 1 1 6 1 1 1)
 *    (1 1 1 1 1)     6         (1 1 6 1 1 1)
 *    ()              32        (32)
 *    (10)            5         (5 10)
 *    (1 17)          85        (1 85 17)
 */
void insertMiddle(ListType & list, int midVal);


/*
 * merge
 *
 * PRE: list1 and list2 are well-formed lists such that each one is
 *      a list of unique values in increasing order
 *
 * returns a list of unique values in increasing order that has all the values 
 *       of the original lists. list1 and list2 are undefined after this operation.
 *
 * Note1: this is required to be a destructive merge: the new list will be made up of nodes of
 * the original list, so this function will not call new or delete, except for the case 
 * mentioned in Note2.
 *    
 * Note2: If list1 and list2 have any values in common, only one of these will end up in the
 * result list and you must reclaim memory for the other one.  (see ***'d examples below)
 * 
 * Note3: your code must use the O(n+m) merge algorithm (discussed in lecture on 10/1)
 *
 * Examples:
 *   list1 before call      list2 before call     merge(list1, list2)
 *   
 *   (2 4 6)               (1 3 5)                (1 2 3 4 5 6)
 *   (1 2 3 4 5)           (6 7 8)                (1 2 3 4 5 6 7 8)
 *   (2 4 6 8 10 12)       (3 6 9 12)             (2 3 4 6 8 9 10 12)     ***
 *   (3 4 5)               (3 4 5)                (3 4 5)                 ***
 *   (1 2 3 4 5)           ()                     (1 2 3 4 5)
 *   ()                    ()                     ()
 *   ()                    (1 2 3 4 5)            (1 2 3 4 5)
 */
ListType merge(ListType list1, ListType list2);


//*************************************************************************

#endif
