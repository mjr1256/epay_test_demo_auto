package epaytest_automated;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Calculator {

	public int add(int num1, int num2) {
		return num1 + num2;
	}


	public int add(int num1, int num2, int num3) {
		return num1 + num2 + num3;
	}

	public int subtract(int num1, int num2, int num3) {
		return num1 - num2 - num3;
	}

	public int subtract(int num1, int num2) {
		return num1 - num2;
	}

	public  int ouverture_adb() {
		try {
			// Commande pour ouvrir l'invite de commande et exécuter "adb devices" dans Windows
			String[] command = {"cmd", "/c", "start", "adb", "devices"};

			// Créer un ProcessBuilder avec la commande spécifiée
			ProcessBuilder processBuilder = new ProcessBuilder(command);

			// Démarrer le processus
			processBuilder.start();

			System.out.println("Invite de commande ouverte avec succès et commande adb devices exécutée.");
			return 0;
		} catch (IOException e) {
			// Gérer l'exception en cas d'erreur lors de l'ouverture de l'invite de commande ou de l'exécution de adb
			System.err.println("Erreur lors de l'ouverture de l'invite de commande ou de l'exécution de adb : " + e.getMessage());
			e.printStackTrace();
			return -2;
		}
	}


	public  int ouverture_adb_sans_pop_up() {

		try {
			// Commande pour ouvrir l'invite de commande en arrière-plan dans Windows
			String[] command = {"cmd", "/c"};

			// Créer un ProcessBuilder avec la commande spécifiée
			ProcessBuilder processBuilder = new ProcessBuilder(command);

			// Rediriger la sortie et l'erreur standard
			processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);

			// Démarrer le processus
			processBuilder.start();

			System.out.println("Invite de commande ouverte en arrière-plan avec succès.");
			return 0;
		} catch (IOException e) {
			// Gérer l'exception en cas d'erreur lors de l'ouverture de l'invite de commande
			System.err.println("Erreur lors de l'ouverture de l'invite de commande : " + e.getMessage());
			e.printStackTrace();
			return -2;
		}
	}

	public int  adb_devices_test() // deroule tous le test 
	{
		try {
			// Commande pour ouvrir l'invite de commande et exécuter des commandes adb dans Windows
			String[] command = {"cmd", "/c", "start", "adb", "devices", "&&", "adb", "root", "&&", "adb", "remount"};

			// Créer un ProcessBuilder avec la commande spécifiée
			ProcessBuilder processBuilder = new ProcessBuilder(command);

			// Rediriger la sortie standard pour la lecture
			processBuilder.redirectErrorStream(true);

			// Démarrer le processus
			Process process = processBuilder.start();

			// Lire la sortie de la commande
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
				System.out.println("Résultat de la commande adb shell epaytest --gtest_filter=IccardTest.psam1 :");
				while ((line = reader.readLine()) != null) {
					// Afficher chaque ligne de sortie
					System.out.println(line);
				}
			}

			// Attendre que le processus se termine
			int exitCode = process.waitFor();

			// Vérifier le code de sortie
			if (exitCode != 0) {
				// La commande s'est terminée avec un code de sortie non nul
				System.err.println("La commande de sortie non nul : " + exitCode);
				return -1;
			} else {
				System.out.println("La commande s'est exécutée avec succès.");
				return 0;
			}
		} catch (IOException | InterruptedException e) {
			// Gérer les exceptions en cas d'erreur lors de l'exécution de la commande adb shell
			System.err.println("Erreur lors de l'exécution de la commande adb shell : " + e.getMessage());
			e.printStackTrace();
			return -1;
		}



	}



	public int executeADBCommands() {
		try {
			// Commandes adb à exécuter
			String[] adbCommands = {"adb devices", "adb root", "adb remount"};

			// Créer un processus pour exécuter les commandes adb
			ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start");
			for (String command : adbCommands) {
				processBuilder.command().add(command);
			}

			Process process = processBuilder.start();

			int exitCode = process.waitFor();

			return exitCode;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int executeADBShellCommand(String adbShellCommand) {
		try {

			ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", "adb", "shell", adbShellCommand);


			processBuilder.redirectErrorStream(true);


			Process process = processBuilder.start();


			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
				System.out.println("Résultat de la commande adb shell " + adbShellCommand + ":");
				while ((line = reader.readLine()) != null) {

					System.out.println(line);
				}
			}


			int exitCode = process.waitFor();


			if (exitCode != 0) {

				System.err.println("La commande adb shell " + adbShellCommand + " s'est terminée avec un code de sortie non nul : " + exitCode);
			} else {
				System.out.println("La commande adb shell " + adbShellCommand + " s'est exécutée avec succès.");
			}

			return exitCode;
		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
			return -1;
		}
	}


	public int runTest3(String testName) {
		try {
			// Ouvrir une invite de commande et afficher le premier message
			ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k", "echo Test en cours : " + testName + " && echo Insérer carte && pause");
			processBuilder.inheritIO(); // Permet à l'utilisateur d'interagir avec l'invite de commande
			processBuilder.start().waitFor(); // Attendre la fin de l'invite de commande
			return 0;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return -1;
		}
	}



	public int  adb_test()
	{
		try {
			// Commande pour ouvrir l'invite de commande et exécuter des commandes adb dans Windows
			String[] command = {"cmd", "/c", "start", "adb", "devices", "&&", "adb", "root", "&&", "adb", "remount", "&&", "adb", "shell", "epaytest", "--gtest_filter=IccardTest.smartcard"};

			// Créer un ProcessBuilder avec la commande spécifiée
			ProcessBuilder processBuilder = new ProcessBuilder(command);

			// Rediriger la sortie standard pour la lecture
			processBuilder.redirectErrorStream(true);

			// Démarrer le processus
			Process process = processBuilder.start();

			// Lire la sortie de la commande
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
				String line;
				System.out.println("Résultat de la commande adb shell epaytest --gtest_filter=IccardTest.psam1 :");
				while ((line = reader.readLine()) != null) {
					// Afficher chaque ligne de sortie
					System.out.println(line);
				}
			}

			// Attendre que le processus se termine
			int exitCode = process.waitFor();

			// Vérifier le code de sortie
			if (exitCode != 0) {
				// La commande s'est terminée avec un code de sortie non nul
				System.err.println("La commande adb shell epaytest --gtest_filter=IccardTest.psam1 s'est terminée avec un code de sortie non nul : " + exitCode);
				return -1;
			} else {
				System.out.println("La commande adb shell epaytest --gtest_filter=IccardTest.psam1 s'est exécutée avec succès.");
				return 0;
			}
		} catch (IOException | InterruptedException e) {
			// Gérer les exceptions en cas d'erreur lors de l'exécution de la commande adb shell
			System.err.println("Erreur lors de l'exécution de la commande adb shell : " + e.getMessage());
			e.printStackTrace();
			return -1;
		}

	}

	
	
    public int adb_test2(String testName) {
        try {
            // Commande pour ouvrir l'invite de commande et exécuter des commandes adb dans Windows
            String[] command = {"cmd", "/c", "start", "adb", "devices", "&&", "adb", "root", "&&", "adb", "remount", "&&", "adb", "shell", "epaytest", "--gtest_filter=" + testName};

            // Créer un ProcessBuilder avec la commande spécifiée
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Rediriger la sortie standard pour la lecture
            processBuilder.redirectErrorStream(true);

            // Démarrer le processus
            Process process = processBuilder.start();

            // Lire la sortie de la commande
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                System.out.println("Résultat de la commande adb shell epaytest --gtest_filter=" + testName + " :");
                while ((line = reader.readLine()) != null) {
                    
                    System.out.println(line);
                }
            }

       
            int exitCode = process.waitFor();

           
            if (exitCode != 0) {
           
                System.err.println("La commande adb shell epaytest --gtest_filter=" + testName + " s'est terminée avec un code de sortie non nul : " + exitCode);
                return -1;
            } else {
                System.out.println("La commande adb shell epaytest --gtest_filter=" + testName + " s'est exécutée avec succès.");
                return 0;
            }
        } catch (IOException | InterruptedException e) {
           
            System.err.println("Erreur lors de l'exécution de la commande adb shell : " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
	
	





}
