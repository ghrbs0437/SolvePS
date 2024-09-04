#include <iostream>
using namespace std;

int get_min(int a, int b, int c) {
	if (a > b) {
		a = b;
	}
	if (a > c) {
		a = c;
	}
	return a;
}

int get_max(int a, int b, int c) {
	if (a < b) {
		a = b;
	}
	if (a < c) {
		a = c;
	}
	return a;
}
int get_middle(int a, int b, int c) {

	int temp[3] = { a,b,c };
	for (int i = 0; i < 3; i++) {
		if ( (temp[i] == get_max(a, b, c)) || (temp[i]== get_min(a, b, c)) ) 
		{
			break;
		}
		a = temp[i];
	}
	return a;
}



struct house {
	int red;
	int green;
	int blue;
};
struct house_case {
	int ca_se1;
	int ca_se2;
	int ca_se3;
};


struct house arr[1001] = { {0,0,0} };
struct house_case arr2[1001] = { {0,0,0} };
int main() {
	int num_house;

	cin >> num_house;
	for (int i = 0;i<num_house; i++) {
		cin >> arr[i].red;
		cin >> arr[i].green;
		cin >> arr[i].blue;
	}
	
	arr2[0].ca_se1 = arr[0].red;
	arr2[0].ca_se2 = arr[0].green;
	arr2[0].ca_se3 = arr[0].blue;
	
	for (int i = 1;i<num_house; i++) {
		arr2[i].ca_se1 = arr[i].red + min(arr2[i - 1].ca_se2,arr2[i - 1].ca_se3);
		arr2[i].ca_se2 = arr[i].green + min(arr2[i - 1].ca_se1, arr2[i - 1].ca_se3);
		arr2[i].ca_se3 = arr[i].blue + min(arr2[i - 1].ca_se1, arr2[i - 1].ca_se2);
	}


	cout << get_min(arr2[num_house-1].ca_se1, arr2[num_house-1].ca_se2, arr2[num_house-1].ca_se3);
	/*cout << arr2[num_house - 1].ca_se1<<endl;
	cout << arr2[num_house - 1].ca_se2<< endl;
	cout << arr2[num_house - 1].ca_se3<< endl;*/
	return 0;
}