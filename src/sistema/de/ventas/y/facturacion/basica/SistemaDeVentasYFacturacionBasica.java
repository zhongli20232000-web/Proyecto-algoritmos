package sistema.de.ventas.y.facturacion.basica;

import java.util.Scanner;

public class SistemaDeVentasYFacturacionBasica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ---------- Variables del menú ----------
        int op;
        int confirmarSalida = 0;

        // ---------- Variables de cada venta (se reinician en cada venta) ----------
        int codigo;
        String nombre;
        double precio;
        int cantidad;
        double subTotal;            
        int cantidadProductos;      
        double totalVenta;          
        double montoDescuento;
        double montoConDescuento;
        double iva;
        double total;
        String descuento;           
        int formaPago;
        String nombrePago = "";
        double pago;
        double cambio;

        // ---------- Contadores y acumuladores generales (caja y estadísticas) ----------
        int cantidadVentas = 0;
        int cantidadProductosVendidos = 0;
        double totalVendido = 0;
        int ventaEfectivo = 0;
        int ventaTarjeta = 0;
        int ventaTransferencia = 0;
        double ventaMayor = 0;
        double ventaMenor = 0;
        double promedio;

        // ---------- Bienvenida ----------
        System.out.println();
        System.out.println("        ____________________");
        System.out.println("       |  ________________  |");
        System.out.println("       | |  Q Q Q Q Q Q Q | |");
        System.out.println("       | |________________| |");
        System.out.println("       |  [7] [8] [9] [+]   |");
        System.out.println("       |  [4] [5] [6] [-]   |");
        System.out.println("       |  [1] [2] [3] [=]   |");
        System.out.println("       |____________________|");
        System.out.println();
        System.out.println("  *.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*");
        System.out.println("  *     BIENVENIDO A LA TIENDA      *");
        System.out.println("  * Sistema de Ventas y Facturacion *");
        System.out.println("  *.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*");

        do {
            System.out.println();
            System.out.println("  +==================================+");
            System.out.println("  |         $$ MENU PRINCIPAL $$     |");
            System.out.println("  +==================================+");
            System.out.println("  |  [1]  Registrar venta            |");
            System.out.println("  |  [2]  Consultar caja             |");
            System.out.println("  |  [3]  Mostrar estadisticas       |");
            System.out.println("  |  [4]  Salir                      |");
            System.out.println("  +==================================+");
            System.out.println("  >> Ingrese una opcion:");
            op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.println();
                    System.out.println("  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("  ~~~~~~~    NUEVA VENTA   ~~~~~~~~~~~");
                    System.out.println("  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                    // Validar: número de productos mayor que cero
                    System.out.println("  >> Cuantos productos lleva el cliente?");
                    cantidadProductos = sc.nextInt();
                    while (cantidadProductos <= 0) {
                        System.out.println("  (!) ERROR: debe llevar al menos 1 producto. Intente de nuevo:");
                        cantidadProductos = sc.nextInt();
                    }

                    // Se reinicia el acumulador de la venta
                    totalVenta = 0;

                    for (int i = 0; i < cantidadProductos; i++) {
                        System.out.println();
                        System.out.println("  [ PRODUCTO " + (i + 1) + " de " + cantidadProductos + " ]");
                        System.out.println("  ------------------------");

                        System.out.println("  > Codigo del producto:");
                        codigo = sc.nextInt();

                        System.out.println("  > Nombre del producto:");
                        nombre = sc.next();

                        // Validar: precio mayor que cero
                        System.out.println("  > Precio del producto (Q):");
                        precio = sc.nextDouble();
                        while (precio <= 0) {
                            System.out.println("  (!) ERROR: el precio debe ser mayor que cero. Intente de nuevo:");
                            precio = sc.nextDouble();
                        }

                        // Validar: cantidad mayor que cero
                        System.out.println("  > Cantidad de unidades:");
                        cantidad = sc.nextInt();
                        while (cantidad <= 0) {
                            System.out.println("  (!) ERROR: la cantidad debe ser mayor que cero. Intente de nuevo:");
                            cantidad = sc.nextInt();
                        }

                        subTotal = precio * cantidad;
                        totalVenta = totalVenta + subTotal;                  
                        cantidadProductosVendidos = cantidadProductosVendidos + cantidad; 

                        System.out.println("  [+] " + nombre + " x" + cantidad + " agregado al carrito  (Q" + Math.round(subTotal * 100.0) / 100.0 + ")");
                    }

                    // ---------- Descuentos ----------
                    System.out.println();
                    System.out.println("  ... calculando descuentos ...");
                    if (totalVenta < 200) {
                        descuento = "0%";
                        montoDescuento = totalVenta * 0;
                        System.out.println("  (sin descuento esta vez, compre mas de Q200 y ahorre!)");
                    } else if (totalVenta < 500) {
                        descuento = "5%";
                        montoDescuento = totalVenta * 0.05;
                        System.out.println("  *** DESCUENTO DEL 5% DESBLOQUEADO! ***");
                    } else if (totalVenta < 1000) {
                        descuento = "8%";
                        montoDescuento = totalVenta * 0.08;
                        System.out.println("  *** WOW! DESCUENTO DEL 8% DESBLOQUEADO! ***");
                    } else {
                        descuento = "10%";
                        montoDescuento = totalVenta * 0.10;
                        System.out.println("  *** CLIENTE VIP! DESCUENTO MAXIMO DEL 10%! ***");
                    }

                    montoConDescuento = totalVenta - montoDescuento;
                    iva = montoConDescuento * 0.12;
                    total = montoConDescuento + iva;

                    // ---------- Forma de pago ----------
                    System.out.println();
                    System.out.println("  +--------------------------+");
                    System.out.println("  |      METODOS DE PAGO     |");
                    System.out.println("  +--------------------------+");
                    System.out.println("  |  [1]  Efectivo           |");
                    System.out.println("  |  [2]  Tarjeta            |");
                    System.out.println("  |  [3]  Transferencia      |");
                    System.out.println("  +--------------------------+");
                    System.out.println("  >> Ingrese la opcion:");
                    formaPago = sc.nextInt();

                    // Validar: opción de pago válida
                    while (formaPago < 1 || formaPago > 3) {
                        System.out.println("  (!) ERROR: opcion invalida, elija 1, 2 o 3:");
                        formaPago = sc.nextInt();
                    }

                    pago = 0;
                    cambio = 0;

                    switch (formaPago) {
                        case 1:
                            nombrePago = "Efectivo";
                            System.out.println("  >> Ingrese el efectivo recibido (Q):");
                            pago = sc.nextDouble();

                            // Validar: efectivo igual o mayor al total
                            while (pago < total) {
                                System.out.println("  (!) EFECTIVO INSUFICIENTE. Faltan: Q" + Math.round((total - pago) * 100.0) / 100.0);
                                System.out.println("  >> Ingrese el pago nuevamente:");
                                pago = sc.nextDouble();
                            }
                            cambio = pago - total;
                            ventaEfectivo++;
                            break;
                        case 2:
                            nombrePago = "Tarjeta";
                            System.out.println("  ... deslizando tarjeta ... bip bip ... APROBADO!");
                            ventaTarjeta++;
                            break;
                        case 3:
                            nombrePago = "Transferencia";
                            System.out.println("  ... verificando transferencia ... RECIBIDA!");
                            ventaTransferencia++;
                            break;
                    }

                    // ---------- Contadores y acumuladores generales ----------
                    cantidadVentas++;
                    totalVendido = totalVendido + total;

                    if (cantidadVentas == 1) {
                        ventaMayor = total;
                        ventaMenor = total;
                    } else {
                        if (total > ventaMayor) {
                            ventaMayor = total;
                        }
                        if (total < ventaMenor) {
                            ventaMenor = total;
                        }
                    }
                    

                    
                    
                    // ---------- Resumen de la venta (estilo ticket) ----------
                    System.out.println();
                    System.out.println("  .--------------------------------.");
                    System.out.println("  |        T I C K E T   D E       |");
                    System.out.println("  |           V E N T A            |");
                    System.out.println("  |--------------------------------|");
                    System.out.println("   Productos ........ " + cantidadProductos);
                    System.out.println("   Subtotal ......... Q" + Math.round(totalVenta * 100.0) / 100.0);
                    System.out.println("   Descuento ........ " + descuento + " (-Q" + Math.round(montoDescuento * 100.0) / 100.0 + ")");
                    System.out.println("   IVA (12%) ........ Q" + Math.round(iva * 100.0) / 100.0);
                    System.out.println("  |--------------------------------|");
                    System.out.println("   TOTAL A PAGAR .... Q" + Math.round(total * 100.0) / 100.0);
                    System.out.println("  |--------------------------------|");
                    System.out.println("   Forma de pago .... " + nombrePago);                   
                    if (formaPago == 1) {
                        System.out.println("   Efectivo ......... Q" + Math.round(pago * 100.0) / 100.0);
                        System.out.println("   Cambio ........... Q" + Math.round(cambio * 100.0) / 100.0);
                    }
                    System.out.println("  |--------------------------------|");
                    System.out.println("  |   * GRACIAS POR SU COMPRA! *   |");
                    System.out.println("  |       Vuelva pronto :)         |");
                    System.out.println("  '--------------------------------'");
                    break;

                case 2:
                    System.out.println();
                    System.out.println("  .================================.");
                    System.out.println("  |     $$  CAJA REGISTRADORA  $$  |");
                    System.out.println("  |================================|");
                    System.out.println("   Ventas realizadas ........ " + cantidadVentas);
                    System.out.println("   Productos vendidos ....... " + cantidadProductosVendidos);
                    System.out.println("   Total vendido ............ Q" + Math.round(totalVendido * 100.0) / 100.0);
                    System.out.println("  |--------------------------------|");
                    System.out.println("   Ventas en efectivo ....... " + ventaEfectivo);
                    System.out.println("   Ventas con tarjeta ....... " + ventaTarjeta);
                    System.out.println("   Ventas por transferencia . " + ventaTransferencia);
                    System.out.println("  '================================'");
                    break;

                case 3:
                    System.out.println();
                    System.out.println("  .================================.");
                    System.out.println("  |    ## E S T A D I S T I C A S  |");
                    System.out.println("  |================================|");
                    if (cantidadVentas == 0) {
                        System.out.println("   No existen ventas registradas.");
                        System.out.println("            (T_T)  ...la caja esta vacia");
                    } else {
                        promedio = totalVendido / cantidadVentas;
                        System.out.println("   Numero total de ventas ........ " + cantidadVentas);
                        System.out.println("   Total de productos vendidos ... " + cantidadProductosVendidos);
                        System.out.println("   Total de dinero vendido ....... Q" + Math.round(totalVendido * 100.0) / 100.0);
                        System.out.println("   Promedio de dinero por venta .. Q" + Math.round(promedio * 100.0) / 100.0);
                        System.out.println("  |--------------------------------|");
                        System.out.println("   Ventas en efectivo ............ " + ventaEfectivo);
                        System.out.println("   Ventas con tarjeta ............ " + ventaTarjeta);
                        System.out.println("   Ventas por transferencia ...... " + ventaTransferencia);
                        System.out.println("  |--------------------------------|");
                        System.out.println("   ^ Venta de MAYOR valor ........ Q" + Math.round(ventaMayor * 100.0) / 100.0);
                        System.out.println("   v Venta de MENOR valor ........ Q" + Math.round(ventaMenor * 100.0) / 100.0);
                    }
                    System.out.println("  '================================'");
                    break;

                case 4:
                    System.out.println();
                    System.out.println("  (?) Esta seguro que desea salir?  [1] Si   [2] No");
                    confirmarSalida = sc.nextInt();
                    break;

                default:
                    System.out.println();
                    System.out.println("  (!) ERROR: opcion incorrecta, elija del 1 al 4.");
                    break;
            }

        } while (confirmarSalida != 1);
        System.out.println();
        System.out.println("  *.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*");
        System.out.println("  *     HASTA LUEGO! VUELVA PRONTO  *");
        System.out.println("  *.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*");
    }
}