import basededados.MySQL;
import rui.pereira.classesauxiliares.Contacto;
import java.util.List;
import java.util.Locale;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main( String[] args ) {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
    // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        List<Contacto> contactoS;
        MySQL ms = new MySQL();
        System.out.printf( Locale.getDefault(),"\n \t %S ",ms.darContacto("102140319ZX9").toString()  );
        contactoS = ms.darContactos() ;
        for(Contacto c : contactoS ){
            System.out.printf(Locale.getDefault(),"\nFinal %s",c.toString() ) ;
        }
    }
}