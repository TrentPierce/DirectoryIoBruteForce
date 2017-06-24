//Author : s-man
//Version : 1.0
//Contact : pwn@live.fr
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.Scanner;

public class DirectoryIoBruteForce {
	public static String randomPage() {
		Random r = new Random();
		int Low = 10;
		int High = 74;
		int Result = r.nextInt(High - Low) + Low;
		String res = 1;
		for (int i = 0; i < Result; i++) {
			//int random = r.nextInt(10 - 0) + 0;
			res ++;
		}
		return res;
	}

	@SuppressWarnings("resource")
	public static String check(String adr,String method) {
		URLConnection url = null;
		String balance = null;
		Scanner a = null;
		try {
			url = new URL("https://blockchain.info/fr/address/" + adr + "?format=json").openConnection();
			a = new Scanner(url.getInputStream());
			for (int i = 0; i < 7; i++) {
				balance = a.nextLine();
			}
			balance = balance.substring(balance.indexOf(":") + 1, balance.lastIndexOf(","));
			if(method.equals("only")){
			if(balance.equals("0")) return "";
			return "Adress : " + adr + " Balance : " + balance+"\n";
			}
			else return "Adress : " + adr + " Balance : " + balance+"\n";
		} catch (IOException e) {

			return "Internet Problems\n";
		}

	}

	public static void init(String method) throws IOException, InterruptedException {

		String page = randomPage();
		while(page.charAt(0)=='0') page=page.substring(1,page.length());
		URLConnection url = new URL("http://directory.io/" + page).openConnection();
		url.setRequestProperty("user-agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
		Scanner A = new Scanner(url.getInputStream());
		String aux = "";
		String adr1 = "";
		String adr2 = "";
		System.out.println(page);
		while (A.hasNext()) {
			aux = A.nextLine();
			if (aux.indexOf("address/") != -1) {
				aux = aux.substring(aux.indexOf("</span>") + 85, aux.lastIndexOf("</span>"));
				adr1 = aux.substring(0, aux.indexOf("</a>"));
				adr2 = aux.substring(aux.lastIndexOf("\">") + 2, aux.lastIndexOf("</a>"));
				adr2 = adr2.replaceAll(" ", "");

				System.out.print(DirectoryIoBruteForce.check(adr1,method));
				System.out.print(DirectoryIoBruteForce.check(adr2,method));

			}
		}

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		if(args.length !=1 || args[0].equals("only")==false && args[0].equals("all")==false ){
			System.out.println("Usage : \njava DirectoryIoBruteForce only : to show only adresses that have balance\njava DirectoryIoBruteForce all : to show only all tested adresses");
		}
		else
		while(true)
			init(args[0]);
	}

}
