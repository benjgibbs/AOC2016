final class Prog {
static int run() {
int a = 0, b = 1, c = 0, d= 0;
L0: {a=1;
L1: {b=1;
L2: {d=26;
L3: {if (c!= 0) break L5;
L4: {if (1!= 0) break L9;
L5: {c=7;
L6: {d++;
L7: {c--;
L8: {if (c!= 0) break L6;
L9: {c=a;
L10: {a++;
L11: {b--;
L12: {if (b!= 0) break L10;
L13: {b=c;
L14: {d--;
L15: {if (d!= 0) break L9;
L16: {c=17;
L17: {d=18;
L18: {a++;
L19: {d--;
L20: {if (d!= 0) break L18;
L21: {c--;
L22: {if (c!= 0) break L17;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
L23: return a;
}
}
