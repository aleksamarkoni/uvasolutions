#include <iostream>
#include <algorithm>

/* Let say that the Knight is marked with the letter K
and the place where Knight can attack on the board is marked with .
for this problem you have only 3 cases
1) if the table is just one row,
KKKKKKKKKKKKKKKKKKKKKKKKK
2) if the table is 2 rows:
KK..KK..KK..KK..
KK..KK..KK..KK..

3) m >= 3 and n >= 3, put knight only on white or black squeare:
K.K.K.K.K
.K.K.K.K.
K.K.K.K.K
*/
int calculate(int m, int n) {
  if (m == 1 && n == 1)
    return 1;
  int min = std::min(m, n);
  int max = std::max(m, n);
  if (min == 1)
    return max;
  if (min == 2)
    return 2 * ((max / 4) * 2 + std::min(max % 4, 2));
  return (m*n+1) / 2;
}

int main() {
  int m, n, min, max, result;
  while (true) {
    std::cin >> m >> n;
    if (!m && !n)
      break;
    result = calculate(m, n);
    std::cout << result << " knights may be placed on a " << m << " row "
      << n << " column board." << std::endl;
  }
}
