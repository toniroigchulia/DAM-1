public class Principal {
    public static void main(String[] args) {
        
        Ptr2 punto1 = new Ptr2(10,10);
        Ptr2 punto2 = new Ptr2(20,25);

        punto1.estePunto();
        System.out.println(punto2.getEjex() + punto2.getEjey());
    }
}