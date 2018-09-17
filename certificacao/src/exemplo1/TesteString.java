package exemplo1;

public class TesteString {

	public static void main(String[] args) {
		StringBuilder nome = new StringBuilder("Rafaela");
		
		System.out.println(nome.reverse());
		
		char[] nome2 = new char[] {'R','A','F','A'};
		
		String nome3 = new String(nome2);
		
		System.out.println(nome3);
		
		System.out.println(15+1+ nome3  + 1500 + "12");
		
		String teste = "Este e ";
		teste += "certo : " + 15 + 10;
		
		System.out.println(teste);
		
		String teste2 = "rafaela";
		teste2.toUpperCase();
		System.out.println(teste2.toUpperCase());
		
		String a = "rafaela";
		String b = "ttttttt";
		
		System.out.println(b.compareTo(a));

	    String w = "rafinha";
	    w.concat(b);
	    System.out.println(w);
		
	}
	
}
