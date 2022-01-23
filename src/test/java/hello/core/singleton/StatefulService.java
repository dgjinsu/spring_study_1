package hello.core.singleton;

import javax.lang.model.SourceVersion;

public class StatefulService {


    public int order(String name, int price){
        System.out.println("name = " + name + "  price = " + price);
//        this.price = price;
        return price;
    }

}
