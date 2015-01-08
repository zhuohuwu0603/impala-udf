package com.zhuohuawu;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        String result = new String("");
        String result1 = new String();
        boolean val1 = (result == result1);
        boolean val2 = (result.equals(result1));
        System.out.println("val1 = " + val1 + ", val2 = " + val2);
    }
}
