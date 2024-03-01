import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите коэффициент p: ");
        long p = scanner.nextLong();

        System.out.print("Введите коэффициент q: ");
        long q = scanner.nextLong();

        scanner.close();

        if(p < 0 && q < 0)
        {
            System.out.println("Уравнение: " + "x^2" + p +"x" + q +"=0");
        }else if(p < 0)
        {
            System.out.println("Уравнение: " + "x^2" + p +"x+" + q +"=0");
        }else if(q < 0)
        {
            System.out.println("Уравнение: " + "x^2+" + p +"x" + q +"=0");
        }
        else
        {
            System.out.println("Уравнение: " + "x^2 +" + p + "x +" + q +"=0");
        }

        long[] roots = findIntegerRoots(p, q);

        if (roots.length == 0) {
            System.out.println("Ответ: решений нет!");
        } else {
            if(roots[0] == roots[1])
            {
                System.out.println("x=" + roots[0]);
            }
            else
            {
                for (int i = 0; i < roots.length; i++) {
                    System.out.println("x" + (i+1) + "= " + roots[i]);
                }
            }

        }

        System.out.print("КВАДРАТНЫЙ КОРЕНЬ С ИСПОЛЬЗОВАНИЕМ ДВОИЧНОГО ПОИСКА: ");
        System.out.print("sqrt 81 = " + sqareRoot(81));
//        System.out.print("sqrt 15 = " + sqareRoot(15));
    }

    static long[] findIntegerRoots(long p, long q) {

        long discriminant = p * p - 4 * q;

        System.out.println("D= " + discriminant);
        System.out.println("Sqrt(" + discriminant + ")= " + sqareRoot(discriminant));

        if (discriminant < 0) {
            return new long[0]; // Нет целых корней
        }

        long sqrtDiscriminant = sqareRoot(discriminant);

        /*
        1) Выполняется непосредственно после вычисления дискриминанта, чтобы быстро определить, имеет ли уравнение вообще действительные корни
        2) Если сумма коэффициента p и квадратного корня из дискриминанта нечетная, то один из корней будет нецелым.
         */

        if (sqrtDiscriminant == -1 || (p + sqrtDiscriminant) % 2 != 0) {
            return new long[0]; // Нет целых корней
        }

        long x1 = (-p + sqrtDiscriminant) / 2;
        long x2 = (-p - sqrtDiscriminant) / 2;

        return new long[]{x1, x2};
    }


    // квадратный корень с использованием двоичного поиска
    static long sqareRoot(long n)
    {
        if (n < 0) {
            return -1; // Неотрицательное число
        }

        long left = 0;
        long right = n;
        long result = -1;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;

            if (square == n) {
                return mid; // Найден квадратный корень
            } else if (square < n) {
                left = mid + 1;
                result = mid; // Потенциальный квадратный корень
            } else {
                right = mid - 1;
            }
        }

        return result;
    }
}