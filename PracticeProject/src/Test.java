public class Test {
    public static void main(String[] args){
        System.out.println("Hello World");
        printHelloWorld();
        printHelloAndMyName("sri");
        System.out.println(sumOfIntegers(2,3));
        System.out.println(prodOfIntegers(2,3));
        System.out.println(divOfIntegers(2,3));
        System.out.println(remainderOfIntegers(2,3));
        System.out.println(subOfIntegers(2,3));
    }
    public static void printHelloWorld()
    {
        System.out.println("Hello World2");
    }
    public static void printHelloAndMyName(String str){
        System.out.println("Hello" + str);
    }
    public static int sumOfIntegers(int a, int b)
    {
        return a+b;
    }
    public static int prodOfIntegers(int a, int b)
    {
        return a*b;
    }
    //division
    public static int divOfIntegers(int a, int b)
    {
        return a/b;
    }
    //remainder
    public static int remainderOfIntegers(int a, int b)
    {
        return a%b;
    }
    public static int subOfIntegers(int a, int b)
            {
                return a-b;
            }

}
