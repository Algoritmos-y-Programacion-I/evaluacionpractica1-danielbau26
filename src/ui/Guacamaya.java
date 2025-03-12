package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }

    /**
     * Permite meterle valores a los arreglos precios y unidades
     * 
     * pre: Los arreglos precios y unidades quedan inicializados
     * post: Los arreglos precios y unidades quedan con valores
     */
    public static void solicitarDatos(){

        for (int i = 0; i<precios.length; i++){
            System.out.println("Digite el precio de la referencia " + (i+1) + ":");
            double precio = reader.nextDouble();
            precios[i] = precio;
            
            System.out.println("Digite la cantidad de productos vendidos de la referencia " + (i+1) + ":");
            int cantidad = reader.nextInt();
            unidades[i] = cantidad;
        }

    }

    /**
     * Permite calcular el total de unidades vendidas en el día
     * 
     * pre: El arreglos unidades deben estar inicializado con valores
     * post: Retorna la cantidad total de unidades venidas en el día
     * @return - 
     */
    public static int calcularTotalUnidadesVendidas(){

        int cantidadTotalUnidades = 0;
        for (int i = 0; i<unidades.length; i++){

            cantidadTotalUnidades += unidades[i];
        }
        return cantidadTotalUnidades;

    }

    /**
     * Permite calcular el precio promedio de las referencias vendidas en el día
     * 
     * pre: Los arreglos precios y unidades deben estar inicializados con valores
     * post: Retorna el precio promedio de las ventas del día
     * @return - precio promedio de las ventas del día
     */
    public static double calcularPrecioPromedio(){

        double precioTotal = 0;
        for (int i = 0; i<precios.length; i++){

            precioTotal += precios[i];
        }

        double precioPromedio = precioTotal/precios.length;

        return precioPromedio;

    }

    /**
     * Permite conocer cuanto fue el valor total de ventas en el día
     * 
     * pre: Los arreglos precios y unidades deben estar inicializados con valores
     * post: retorna las ventas totales realizadas en el día
     * @return - dinero de las ventas totales en el día
     */
    public static double calcularVentasTotales(){

        double ventasTotales = 0;
        for (int i = 0; i<unidades.length; i++){
            ventasTotales += unidades[i] * precios[i];
        }

        return ventasTotales;

    }

    /**
     * Permite saber cuantos productos superan el limite digitado por el usuario
     * 
     * pre: Los arreglos precios y unidades deben estar inicializados con valores
     * post: Retorna el valor de cuantos productos superaron el limite
     * @param limite - un numero double limite>=0
     * @return - cantidad de productos que superaron el limite
     */
    public static int consultarReferenciasSobreLimite(double limite){

        int superaronLimite = 0;
        for (int i = 0; i<unidades.length; i++ ){

            if (precios[i]*unidades[i] > limite){

                superaronLimite += 1;
            }
        }
        return superaronLimite;

    }

}
