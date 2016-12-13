#include <stdio.h>
int main(int argc, char** argv) {
int a = 0, b = 0, c = 1, d= 0;
L0: a=1;
L1: b=1;
L2: d=26;
L3: if (c!= 0) goto L5;
L4: if (1!= 0) goto L9;
L5: c=7;
L6: d++;
L7: c--;
L8: if (c!= 0) goto L6;
L9: c=a;
L10: a++;
L11: b--;
L12: if (b!= 0) goto L10;
L13: b=c;
L14: d--;
L15: if (d!= 0) goto L9;
L16: c=17;
L17: d=18;
L18: a++;
L19: d--;
L20: if (d!= 0) goto L18;
L21: c--;
L22: if (c!= 0) goto L17;
L23: printf("a=%d\n",a);
}
