public class A {

//private String peselarray;

  //  public A(String peselarray[]) {
     //   this.peselarray[] = peselarray[];
   // }

    public static boolean checkpesel(String pesel) {

        int[] check = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        String peselarray[] = pesel.split("");
        int numpesel[] = new int[peselarray.length];
        for (int i = 0; i < peselarray.length; i++) {
            numpesel[i] = Integer.parseInt(peselarray[i]);
        }

        if (numpesel.length != 11) {
            System.out.println("pesel too short");
            return false;
        } else {
            int sum = 0;//
            for (int i = 0; i < 10; i ++) {
                sum = sum + numpesel[i] * check[i];
            }
            sum = sum % 10;
            if (sum != 0) {
                sum = 10 - sum;
            }
            if (sum != numpesel[10]) {
                System.out.println("pesel is wrong");
                return false;
            } else {
                return true;
            }
        }
    }
}
