#include <stdio.h>
#include <stdlib.h>

int is_same_square(int x, int y, int x1, int y1) {
  return x == x1 && y == y1;
}

int is_between(int a, int b, int c) {
  return (b > a && b < c) || (b > c && b < a);
}

int main() {
  int k, q, qm;
  while (scanf("%d %d %d", &k, &q, &qm) != EOF) {
    if (k == q) {
      printf("Illegal state\n");
      continue;
    }
    if (k == qm || q == qm) {
      printf("Illegal move\n");
      continue;
    }
    /* calculate position of king and queen in x/y plane based on given numbers */
    int kx, ky, qx, qy, qmx, qmy;
    kx = k % 8; qx = q % 8; qmx = qm % 8;
    ky = k / 8; qy = q / 8; qmy = qm / 8;
    if (qx != qmx && qy != qmy) {
      printf("Illegal move\n");
      continue;
    }

    /* if queen moved by the y row */
    if (qx != qmx) {
      /* if the king is on the y row */
      if (ky == qmy && ky == qy && is_between(qx, kx, qmx)) {
        /* if the queen move was behind king */
        if (abs(kx-qx) <= abs(qmx-qx)) {
          printf("Illegal move\n");
          continue;
        }
      }
    }

    if (qy != qmy) {
      /* if the king is on the y row */
      if (kx == qmx && kx == qx && is_between(qy, ky, qmy)) {
        /* if the queen move was behind king */
        if (abs(ky-qy) <= abs(qmy-qy)) {
          printf("Illegal move\n");
          continue;
        }
      }
    }

    if (is_same_square(qmx, qmy, kx+1, ky) || is_same_square(qmx, qmy, kx, ky+1)
      || is_same_square(qmx, qmy, kx-1, ky) || is_same_square(qmx, qmy, kx, ky-1)) {
        printf("Move not allowed\n");
        continue;
      }

    /* Check to see if king is locked */
    if (kx+1 < 8 && kx+1 != qmx) {
      printf("Continue\n");
      continue;
    }

    if (kx-1 >=0 && kx-1 != qmx) {
      printf("Continue\n");
      continue;
    }

    if (ky+1 < 8 && ky+1 != qmy) {
      printf("Continue\n");
      continue;
    }

    if (ky-1 >=0 && ky-1 != qmy) {
      printf("Continue\n");
      continue;
    }
    printf("Stop\n");
  }
}
