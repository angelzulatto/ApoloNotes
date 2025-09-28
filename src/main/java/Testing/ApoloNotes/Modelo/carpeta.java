import.java.io.File;
import.java.io.IOException;

public class carpeta {

    String ruta = "C:\Users\Angel\Desktop\testCarpeta";
    //File carpeta = new File(rutaCarpeta);
//
    //if (!carpeta.exists()){
    //    boolean creada = carpeta.mkdir();
//
    //    if (creada){
    //        System.out.println("Creada: "+ ruta.Carpeta);
    //    }else{
    //        System.out.println("No creada");
    //    }
    //}else{
    //    System.out.println("Ya existe" + rutaCarpeta);
    //}

    public void guardar(){

        @Override
        try{
            if (!Files.exist(ruta)){
                Files.createDirectories(ruta);
                System.out.println("Creada: " + ruta.toAbsolutePath());
            }else{
                System.out.println("Ya existe: " + ruta.toAbsolutePath());
            }
        }catch (IOException e){
            System.err.println ("Error");
        }
    }

}
