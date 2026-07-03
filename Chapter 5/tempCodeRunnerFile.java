int choice = 0;
            while (sc.hasNextInt()) {
                if (choice >= 1 && choice <= 5) {
                    choice = sc.nextInt();
                } else {
                    System.out.println("Invalid input. Try again.");
                    sc.nextLine();
                }
            }