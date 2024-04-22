package test;


class A {
    public void f() {
        System.out.println("1");
    }

    public void g() {
        System.out.println("2");
    }

}

class B extends A {
    public void f() {
        System.out.println("3");
    }
}

class C extends B {
    public void g() {
        System.out.println("4");
    }
}

public class Test {
    public static void main(String[] args) {
        A obj = new C();
        obj.f();
        obj.g();

    }



}
