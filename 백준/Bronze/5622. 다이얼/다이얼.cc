#include <iostream>
#include <stdio.h>
#include <string>
using namespace std;

int table[26] = { 3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,8,9,9,9,10,10,10,10 };

int main() {
	
	int time=0;
	char park[16]={0};
	
	scanf("%s",park);
	
	int cnt = 0;
	for (int i = 0; i < sizeof(park); i++) 
	{
			time += table[park[i] - 'A'];
	}
	cout << time;

	return 0;
}