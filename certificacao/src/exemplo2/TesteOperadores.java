package exemplo2;

import java.util.ArrayList;

public class TesteOperadores {

	public static void main(String[] args) {
		
		ArrayList<String> lista = new ArrayList<>();
		
		//int i = -500;
		//int v = 0;
		
		//System.out.println(i/0.0 );
		
		int i = 10;
		String mensagem = i%2 == 0 ? "Par" : "impar";
		System.out.println(i+" � "+mensagem);
		
	}
}
