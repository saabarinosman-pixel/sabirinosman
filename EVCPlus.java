import java.util.Scanner;

    public class EVCPlus {

        // USER DETAILS

        static int userPin = 12345;
        static final long userMobile = 614940350;

        static final String EVC_DIAL = "*770#";
        static final String SHOW_MOBILE = "*999#";
        static final String SHOW_BALANCE = "*711#";

        static double balance = 60;
        static double bankBalance = 250;
        static boolean accountLocked = false;
        static String language = "SO";

        static String lastAction = "Ma jiro wax dhaqdhaqaaq ah weli";


        static final int MAX_HISTORY = 50;

        static String[] sentHistory = new String[MAX_HISTORY];
        static int sentHistoryCount = 0;

        static String[] receivedHistory = new String[MAX_HISTORY];
        static int receivedHistoryCount = 0;

        static String[] balanceHistory = new String[MAX_HISTORY];
        static int balanceHistoryCount = 0;

        static Scanner input = new Scanner(System.in);


        // MAIN

        public static void main(String[] args) {

            System.out.println("------------------");
            System.out.println("   Available Options");
            System.out.println("   *770# EvcDial");
            System.out.println("   *711# balance");
            System.out.println("   *999# mobile");
            System.out.println("------------------");

            dialNumber();
        }


        // DIALING

        public static void dialNumber() {

            System.out.println("?");
            String dial = input.nextLine();

            if (dial.equals(EVC_DIAL)) {
                checkPin();

            } else if (dial.equals(SHOW_MOBILE)) {
                System.out.println("MSISDN: 252" + userMobile);

            } else if (dial.equals(SHOW_BALANCE)) {
                System.out.print("-EVCPLUS-\nFadlan geli pin-kaaga (Enter PIN): ");
                int pin = readInt();

                if (pin == userPin) {
                    System.out.println("<-EVCPlus-> Haraagaagu waa $" + balance);
                } else {
                    System.out.println("PIN kaagu waa qalad");
                }

            } else {
                System.out.println("Connection problem or invalid MMI code.");

                boolean keepAsking = true;
                while (keepAsking) {
                    System.out.print("Phone: ");
                    dial = input.nextLine();

                    if (dial.equals(EVC_DIAL)) {
                        keepAsking = false;
                    } else {
                        System.out.println("Connection problem or invalid MMI code.");
                    }
                }
                checkPin();
            }
        }


        public static void checkPin() {

            if (accountLocked) {
                System.out.println("<-EVCPlus-> Xisaabtaadu waa xidhan tahay. Fadlan la xiriir 141.");
                return;
            }

            System.out.print("-EVCPLUS-\nFadlan geli pin-kaaga (Enter PIN): ");
            int pin = readInt();

            if (pin == userPin) {
                menu();
            } else {
                System.out.println("PIN kaagu waa qalad");
            }
        }
        static int readInt() {
            while (!input.hasNextInt()) {
                System.out.print("Fadlan geli number sax ah: ");
                input.nextLine();
            }
            int value = input.nextInt();
            input.nextLine();
            return value;
        }


        // Keeps asking until the user types a valid decimal number, then returns it.
        static double readDouble() {
            while (true) {
                String line = input.nextLine();
                try {
                    return Double.parseDouble(line);
                } catch (NumberFormatException e) {
                    System.out.print("Fadlan geli lacag sax ah: ");
                }
            }
        }


        // Checks that a mobile number is exactly 9 digits and starts with 61 or 77.
        static boolean isValidMobile(String mobileStr) {
            if (mobileStr == null) {
                return false;
            }
            if (mobileStr.length() != 9) {
                return false;
            }
            if (!mobileStr.matches("\\d+")) {
                return false;
            }
            String prefix = mobileStr.substring(0, 2);
            return prefix.equals("61") || prefix.equals("77");
        }


        static String readValidMobile() {
            while (true) {
                System.out.print("Fadlan geli mobile-ka (tusaale: 61XXXXXXX ama 77XXXXXXX): ");
                String mobileStr = input.nextLine();

                if (isValidMobile(mobileStr)) {
                    return mobileStr;
                } else {
                    System.out.println("Lambarkaas waa qalad. Waa inuu bilaabmaa 61 ama 77 oo ka kooban yahay 9 lambar.");
                }
            }
        }


        // Shows a yes/no question and returns true if the user picked "1" (yes).
        static boolean confirm(String message) {
            System.out.println(message);
            System.out.println("1. Haa");
            System.out.println("2. Maya");
            String choice = input.nextLine();
            return choice.equals("1");
        }


        // Adds one line of text into a history array, as long as there is room left.
        static void addToHistory(String[] historyArray, int count, String newEntry) {
            if (count < historyArray.length) {
                historyArray[count] = newEntry;
            }
        }


        // Prints out everything stored so far in a history array.
        static void printHistory(String[] historyArray, int count, String title) {
            System.out.println("== " + title + " ==");

            if (count == 0) {
                System.out.println("Ma jiro diiwaan.");
            } else {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + historyArray[i]);
                }
            }
            backToMenu();
        }


        static void backToMenu() {
            System.out.println();
            System.out.println("Taabo 0 si aad ugu noqoto menu-ga, wax kale oo dhan si aad uga baxdo.");
            String choice = input.nextLine();

            if (choice.equals("0")) {
                menu();
            } else {
                System.out.println("Mahadsanid, EVC Plus.");
            }
        }


        // MAIN MENU

        public static void menu() {

            System.out.println("EVCPlus");
            System.out.println("1. Itus Haraaga");
            System.out.println("2. Kaarka hadalka");
            System.out.println("3. Bixi biil");
            System.out.println("4. U wareeji EVCPLUS");
            System.out.println("5. Warbixin kooban");
            System.out.println("6. Salaam Bank");
            System.out.println("7. Maareynta");
            System.out.println("8. Taaj");
            System.out.println("9. Bill payment");
            System.out.println("0. Ka bax");

            String option = input.nextLine();

            if (option.equals("1")) {
                showBalance();
            } else if (option.equals("2")) {
                kaarkaHadalkaMenu();
            } else if (option.equals("3")) {
                bixiBiilMenu();
            } else if (option.equals("4")) {
                uWareejiMenu();
            } else if (option.equals("5")) {
                warbixinMenu();
            } else if (option.equals("6")) {
                salaamBankMenu();
            } else if (option.equals("7")) {
                maamulkaMenu();
            } else if (option.equals("8")) {
                taajMenu();
            } else if (option.equals("9")) {
                billPaymentMenu();
            } else if (option.equals("0")) {
                System.out.println("Mahadsanid, EVC Plus.");
            } else {
                System.out.println("Fadlan dooro number sax ah");
                menu();
            }
        }


        public static void showBalance() {
            System.out.println("<-EVCPlus-> Haraagaagu waa $" + balance);
            lastAction = "Hubinta haraaga: $" + balance;
            backToMenu();
        }


        // 2. KAARKA HADALKA

        public static void kaarkaHadalkaMenu() {

            System.out.println("Kaarka Hadalka");
            System.out.println("1. Ku shubo Airtime");
            System.out.println("2. Ugu shub qof kale");
            System.out.println("3. MIFI packages");
            System.out.println("4. Ku shubo internet");
            System.out.println("5. Ugu shub qof kale (MMT)");
            System.out.println("0. Dib u noqo");

            String option = input.nextLine();

            if (option.equals("1")) {
                kuShubo();
            } else if (option.equals("2")) {
                uguShub();
            } else if (option.equals("3")) {
                mifiPackage();
            } else if (option.equals("4")) {
                kushuboInternet();
            } else if (option.equals("5")) {
                uguShubMMT();
            } else if (option.equals("0")) {
                menu();
            } else {
                System.out.println("Fadlan dooro number sax ah");
                kaarkaHadalkaMenu();
            }
        }


        public static void kuShubo() {

            int count = 0;
            System.out.print("Fadlan geli lacagta: ");
            String currencyInput = input.nextLine();
            double currency = 0;

            boolean valid = false;
            while (!valid) {
                try {
                    currency = Double.parseDouble(currencyInput);
                    valid = true;
                } catch (NumberFormatException e) {
                    count++;
                    if (count == 4) {
                        System.out.println("Waxaad gaartay inta ku noqosho laguu ogolaa,\nFadlan dib usoo gal");
                        return;
                    }
                    System.out.print("You can only enter a number!.\nFadlan geli lacagta: ");
                    currencyInput = input.nextLine();
                }
            }

            boolean ok = confirm("Ma hubtaa inaad $" + currency + " ugu shubtid lambarkaaga (252" + userMobile + ")?");

            if (ok) {
                if (currency > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - currency;
                    System.out.println("Waxaad $" + currency + " ku shubtay lambarkaaga, haraagaagu hadda waa $" + balance);
                    lastAction = "Ku shubay $" + currency + " lambarkaaga";
                    addToHistory(balanceHistory, balanceHistoryCount, "Airtime own: -$" + currency + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void uguShub() {

            // Number must be exactly 9 digits and start with 61 or 77
            String mobileStr = readValidMobile();
            long mobile = Long.parseLong(mobileStr);

            System.out.print("Fadlan geli lacagta: ");
            double amount = readDouble();

            boolean ok = confirm("Ma hubtaa inaad $" + amount + " ugu shubtid 252" + mobile + "?");

            if (ok) {
                if (amount > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - amount;
                    System.out.println("Waxaad $" + amount + " ugu shubtay 252" + mobile + ", haraagaagu hadda waa $" + balance);
                    lastAction = "Ku shubay $" + amount + " -> 252" + mobile;

                    addToHistory(sentHistory, sentHistoryCount, "Airtime -> 252" + mobile + ": $" + amount);
                    sentHistoryCount++;

                    addToHistory(balanceHistory, balanceHistoryCount, "Airtime to 252" + mobile + ": -$" + amount + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void mifiPackage() {

            System.out.println("MIFI Packages");
            System.out.println("1. 5GB  - $5  (30 maalmood)");
            System.out.println("2. 10GB - $9  (30 maalmood)");
            System.out.println("3. 20GB - $16 (30 maalmood)");
            System.out.println("0. Dib u noqo");

            String choice = input.nextLine();
            double price;

            if (choice.equals("1")) {
                price = 5;
            } else if (choice.equals("2")) {
                price = 9;
            } else if (choice.equals("3")) {
                price = 16;
            } else if (choice.equals("0")) {
                kaarkaHadalkaMenu();
                return;
            } else {
                System.out.println("Fadlan dooro package sax ah");
                return;
            }

            if (confirm("Ma hubtaa inaad iibsatid package-kan $" + price + "?")) {
                if (price > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - price;
                    System.out.println("Package-ka waa la iibsaday. Haraagaagu hadda waa $" + balance);
                    lastAction = "MIFI package $" + price;
                    addToHistory(balanceHistory, balanceHistoryCount, "MIFI package: -$" + price + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void kushuboInternet() {

            System.out.println("Ku shubo Internet");
            System.out.println("1. Isbuucle (weekly)");
            System.out.println("2. Maalinle (daily)");
            System.out.println("3. Bille (monthly)");
            System.out.println("4. Custom package");
            System.out.println("0. Dib u noqo");

            String option = input.nextLine();

            if (option.equals("1")) {
                isbuucle();
            } else if (option.equals("2")) {
                maalinle();
            } else if (option.equals("3")) {
                bille();
            } else if (option.equals("4")) {
                customPackage();
            } else if (option.equals("0")) {
                kaarkaHadalkaMenu();
            } else {
                System.out.println("Fadlan dooro number sax ah");
                kushuboInternet();
            }
        }


        public static void isbuucle() {
            purchaseInternet("Isbuucle (7 maalmood, 2GB)", 3);
        }


        public static void maalinle() {
            purchaseInternet("Maalinle (1 maalin, 300MB)", 1);
        }


        public static void bille() {
            purchaseInternet("Bille (30 maalmood, 8GB)", 10);
        }


        public static void customPackage() {
            System.out.print("Fadlan geli lacagta package-ga aad rabto: ");
            double amount = readDouble();
            purchaseInternet("Custom Package", amount);
        }


        static void purchaseInternet(String name, double price) {

            if (confirm("Ma hubtaa inaad iibsatid \"" + name + "\" oo qiimihiisu yahay $" + price + "?")) {
                if (price > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - price;
                    System.out.println(name + " waa la iibsaday. Haraagaagu hadda waa $" + balance);
                    lastAction = name + " $" + price;
                    addToHistory(balanceHistory, balanceHistoryCount, name + ": -$" + price + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void uguShubMMT() {

            System.out.print("Fadlan geli mobile-ka MMT (qof kale): ");
            String mobileStr = input.nextLine();

            System.out.print("Fadlan geli lacagta: ");
            double amount = readDouble();

            if (confirm("Ma hubtaa inaad $" + amount + " ugu shubtid MMT ee " + mobileStr + "?")) {
                if (amount > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - amount;
                    System.out.println("MMT waa la shubay. Haraagaagu hadda waa $" + balance);
                    lastAction = "MMT -> " + mobileStr + " $" + amount;

                    addToHistory(sentHistory, sentHistoryCount, "MMT -> " + mobileStr + ": $" + amount);
                    sentHistoryCount++;

                    addToHistory(balanceHistory, balanceHistoryCount, "MMT to " + mobileStr + ": -$" + amount + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        // 3. BIXI BIIL

        public static void bixiBiilMenu() {

            System.out.println("Bixi Biil");
            System.out.println("1. Postpaid");
            System.out.println("2. Korontada (Electricity)");
            System.out.println("3. Biyaha (Water)");
            System.out.println("4. Xaqiiji lacag bixinta (Confirm payment)");
            System.out.println("0. Dib u noqo");

            String option = input.nextLine();

            if (option.equals("1")) {
                postPaid();
            } else if (option.equals("2")) {
                electricity();
            } else if (option.equals("3")) {
                water();
            } else if (option.equals("4")) {
                confirmPayment();
            } else if (option.equals("0")) {
                menu();
            } else {
                System.out.println("Fadlan dooro number sax ah");
                bixiBiilMenu();
            }
        }


        public static void postPaid() {
            payBill("Postpaid");
        }


        public static void electricity() {
            payBill("Korontada");
        }


        public static void water() {
            payBill("Biyaha");
        }


        static String lastBillType = "";
        static double lastBillAmount = 0;


        static void payBill(String type) {

            System.out.print("Fadlan geli lambarka account-ka " + type + ": ");
            String account = input.nextLine();

            System.out.print("Fadlan geli lacagta biilka: ");
            double amount = readDouble();

            if (confirm("Ma hubtaa inaad bixiso $" + amount + " biil " + type + " (account: " + account + ")?")) {
                if (amount > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - amount;
                    lastBillType = type;
                    lastBillAmount = amount;
                    System.out.println("Biilka " + type + " waa la bixiyay. Haraagaagu hadda waa $" + balance);
                    lastAction = "Bixiyay biil " + type + " $" + amount;
                    addToHistory(balanceHistory, balanceHistoryCount, "Bill " + type + ": -$" + amount + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void confirmPayment() {

            if (lastBillType.equals("")) {
                System.out.println("Ma jiro lacag bixin dhawaan la sameeyay.");
            } else {
                System.out.println("Lacag bixintii ugu dambeysay: " + lastBillType + " $" + lastBillAmount + " - GUUL (SUCCESS)");
            }
            backToMenu();
        }


        // 4. U WAREEJI ftfc

        static long lastReceiver = 0;
        static double lastTransferAmount = 0;


        public static void uWareejiMenu() {

            long receiver = enterReceiver();

            double amount = enterAmount();
            boolean confirmed = confirmTransfer(receiver, amount);

            if (confirmed) {
                transferReceipt(receiver, amount);
            } else {
                System.out.println("Mahadsanid");
                backToMenu();
            }
        }


        public static long enterReceiver() {
            // Number must be exactly 9 digits and start with 61 or 77
            String mobileStr = readValidMobile();
            return Long.parseLong(mobileStr);
        }


        public static double enterAmount() {
            System.out.print("Fadlan geli lacagta: ");
            return readDouble();
        }


        public static boolean confirmTransfer(long receiver, double amount) {
            lastReceiver = receiver;
            lastTransferAmount = amount;
            return confirm("Ma hubtaa inaad $" + amount + " u wareejiso 252" + receiver + "?");
        }


        public static void transferReceipt(long receiver, double amount) {

            if (amount > balance) {
                System.out.println("Haraaga xisaabtaadu kuguma filna.");
            } else {
                balance = balance - amount;

                System.out.println("-EVCPlus- RASIID");
                System.out.println("Waxaad u wareejisay: $" + amount);
                System.out.println("Kuwa hela: 252" + receiver);
                System.out.println("Haraaga hadda: $" + balance);

                lastAction = "U wareejiyay $" + amount + " -> 252" + receiver;

                addToHistory(sentHistory, sentHistoryCount, "Transfer -> 252" + receiver + ": $" + amount);
                sentHistoryCount++;

                addToHistory(balanceHistory, balanceHistoryCount, "Transfer to 252" + receiver + ": -$" + amount + " -> $" + balance);
                balanceHistoryCount++;
            }
            backToMenu();
        }


        // 5. WARBIXIN KOOBAN

        public static void warbixinMenu() {

            System.out.println("Warbixin Kooban");
            System.out.println("1. Dhaqdhaqaaqii ugu dambeeyay");
            System.out.println("2. Taariikhda la diray");
            System.out.println("3. Taariikhda la helay");
            System.out.println("4. Taariikhda haraaga");
            System.out.println("0. Dib u noqo");

            String option = input.nextLine();

            if (option.equals("1")) {
                showLastAction();
            } else if (option.equals("2")) {
                showSentHistory();
            } else if (option.equals("3")) {
                showReceivedHistory();
            } else if (option.equals("4")) {
                showBalanceHistory();
            } else if (option.equals("0")) {
                menu();
            } else {
                System.out.println("Fadlan dooro number sax ah");
                warbixinMenu();
            }
        }


        public static void showLastAction() {
            System.out.println("Dhaqdhaqaaqii ugu dambeeyay: " + lastAction);
            backToMenu();
        }


        public static void showSentHistory() {
            printHistory(sentHistory, sentHistoryCount, "Taariikhda la diray");
        }


        public static void showReceivedHistory() {
            printHistory(receivedHistory, receivedHistoryCount, "Taariikhda la helay");
        }


        public static void showBalanceHistory() {
            printHistory(balanceHistory, balanceHistoryCount, "Taariikhda haraaga");
        }


        // 6. SALAAM BANK

        public static void salaamBankMenu() {

            System.out.println("Salaam Bank");
            System.out.println("1. Deposit (EVC -> Bank)");
            System.out.println("2. Withdraw (Bank -> EVC)");
            System.out.println("3. U wareeji Bank");
            System.out.println("4. Macluumaadka account-ka");
            System.out.println("0. Dib u noqo");

            String option = input.nextLine();

            if (option.equals("1")) {
                deposit();
            } else if (option.equals("2")) {
                withdraw();
            } else if (option.equals("3")) {
                transferBank();
            } else if (option.equals("4")) {
                accountInfo();
            } else if (option.equals("0")) {
                menu();
            } else {
                System.out.println("Fadlan dooro number sax ah");
                salaamBankMenu();
            }
        }


        public static void deposit() {

            System.out.print("Fadlan geli lacagta aad deposit-garayso: ");
            double amount = readDouble();

            if (confirm("Ma hubtaa inaad $" + amount + " kaga wareejiso EVC una geeyso Salaam Bank?")) {
                if (amount > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - amount;
                    bankBalance = bankBalance + amount;
                    System.out.println("Deposit waa guuleystay. EVC: $" + balance + " | Bank: $" + bankBalance);
                    lastAction = "Deposit $" + amount + " -> Salaam Bank";
                    addToHistory(balanceHistory, balanceHistoryCount, "Deposit to bank: -$" + amount + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void withdraw() {

            System.out.print("Fadlan geli lacagta aad withdraw-garayso: ");
            double amount = readDouble();

            if (confirm("Ma hubtaa inaad $" + amount + " ka soo bixiso Salaam Bank una geeyso EVC?")) {
                if (amount > bankBalance) {
                    System.out.println("Haraaga bank-kaagu kuguma filna.");
                } else {
                    bankBalance = bankBalance - amount;
                    balance = balance + amount;
                    System.out.println("Withdraw waa guuleystay. EVC: $" + balance + " | Bank: $" + bankBalance);
                    lastAction = "Withdraw $" + amount + " <- Salaam Bank";
                    addToHistory(balanceHistory, balanceHistoryCount, "Withdraw from bank: +$" + amount + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void transferBank() {

            System.out.print("Fadlan geli lambarka account-ka la wareejinayo: ");
            String account = input.nextLine();

            System.out.print("Fadlan geli lacagta: ");
            double amount = readDouble();

            if (confirm("Ma hubtaa inaad $" + amount + " ugu wareejiso account-ka " + account + "?")) {
                if (amount > bankBalance) {
                    System.out.println("Haraaga bank-kaagu kuguma filna.");
                } else {
                    bankBalance = bankBalance - amount;
                    System.out.println("Wareejinta waa la sameeyay. Bank balance-kaagu hadda waa $" + bankBalance);
                    lastAction = "Bank transfer $" + amount + " -> " + account;
                    addToHistory(sentHistory, sentHistoryCount, "Bank transfer -> " + account + ": $" + amount);
                    sentHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void accountInfo() {
            System.out.println("-Salaam Bank-");
            System.out.println("Magaca account-ka: EVC Plus User");
            System.out.println("Lambarka: 252" + userMobile);
            System.out.println("Haraaga bank-ka: $" + bankBalance);
            backToMenu();
        }


        // 7. MAAREYNTA

        public static void maamulkaMenu() {

            System.out.println("Maareynta");
            System.out.println("1. Beddel PIN");
            System.out.println("2. Beddel luqadda");
            System.out.println("3. Xidh account-ka");
            System.out.println("4. Fur account-ka");
            System.out.println("0. Dib u noqo");

            String option = input.nextLine();

            if (option.equals("1")) {
                changePIN();
            } else if (option.equals("2")) {
                changeLanguage();
            } else if (option.equals("3")) {
                lockAccount();
            } else if (option.equals("4")) {
                unlockAccount();
            } else if (option.equals("0")) {
                menu();
            } else {
                System.out.println("Fadlan dooro number sax ah");
                maamulkaMenu();
            }
        }


        public static void changePIN() {

            System.out.print("Fadlan geli PIN-kaaga hadda: ");
            int oldPin = readInt();

            if (oldPin != userPin) {
                System.out.println("PIN kaagu waa qalad");
                backToMenu();
                return;
            }

            System.out.print("Fadlan geli PIN cusub (5 lambar): ");
            int newPin = readInt();

            System.out.print("Ku celi PIN cusub: ");
            int confirmPin = readInt();

            if (newPin == confirmPin) {
                userPin = newPin;
                System.out.println("PIN-kaaga waa la beddelay.");
                lastAction = "Beddelay PIN";
            } else {
                System.out.println("PIN-yadu isku mid ma aha, isku day mar kale.");
            }
            backToMenu();
        }


        public static void changeLanguage() {

            System.out.println("Beddel luqadda");
            System.out.println("1. Somali");
            System.out.println("2. English");

            String choice = input.nextLine();

            if (choice.equals("1")) {
                language = "SO";
                System.out.println("Luqadda waxaa loo beddelay Somali.");
            } else if (choice.equals("2")) {
                language = "EN";
                System.out.println("Language changed to English.");
            } else {
                System.out.println("Fadlan dooro number sax ah");
            }

            lastAction = "Changed language to " + language;
            backToMenu();
        }


        public static void lockAccount() {

            if (confirm("Ma hubtaa inaad xidhid account-kaaga?")) {
                accountLocked = true;
                System.out.println("Account-kaagu waa xidhan yahay. Wac 141 si aad u furto.");
                lastAction = "Xidhay account-ka";
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void unlockAccount() {
            accountLocked = false;
            System.out.println("Account-kaagu waa la furay.");
            lastAction = "Furay account-ka";
            backToMenu();
        }


        // 8. TAAJ

        public static void taajMenu() {

            System.out.println("Taaj");
            System.out.println("1. Hel lacag (Receive money)");
            System.out.println("2. Dir lacag (Send money)");
            System.out.println("3. Xaalada dhaqdhaqaaqa (Transaction status)");
            System.out.println("0. Dib u noqo");

            String option = input.nextLine();

            if (option.equals("1")) {
                receiveMoney();
            } else if (option.equals("2")) {
                sendMoney();
            } else if (option.equals("3")) {
                transactionStatus();
            } else if (option.equals("0")) {
                menu();
            } else {
                System.out.println("Fadlan dooro number sax ah");
                taajMenu();
            }
        }


        static String lastTaajRef = "";


        public static void receiveMoney() {

            System.out.print("Fadlan geli lambarka Taaj (reference code): ");
            String code = input.nextLine();

            System.out.print("Fadlan geli lacagta la sugayo: ");
            double amount = readDouble();

            balance = balance + amount;
            lastTaajRef = code;

            System.out.println("Waxaad heshay $" + amount + " (Taaj: " + code + "). Haraagaagu hadda waa $" + balance);
            lastAction = "Taaj: helay $" + amount;

            addToHistory(receivedHistory, receivedHistoryCount, "Taaj (" + code + "): $" + amount);
            receivedHistoryCount++;

            addToHistory(balanceHistory, balanceHistoryCount, "Taaj received: +$" + amount + " -> $" + balance);
            balanceHistoryCount++;

            backToMenu();
        }


        public static void sendMoney() {

            System.out.print("Fadlan geli lacagta aad dirayso: ");
            double amount = readDouble();

            if (confirm("Ma hubtaa inaad Taaj u dirtid $" + amount + "?")) {
                if (amount > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - amount;

                    // build a simple random reference code, e.g. "TJ482913"
                    int randomNumber = (int) (Math.random() * 900000 + 100000);
                    lastTaajRef = "TJ" + randomNumber;

                    System.out.println("Waxaad dirtay $" + amount + ". Reference code-kaagu waa: " + lastTaajRef);
                    lastAction = "Taaj: dirtay $" + amount;

                    addToHistory(sentHistory, sentHistoryCount, "Taaj (" + lastTaajRef + "): $" + amount);
                    sentHistoryCount++;

                    addToHistory(balanceHistory, balanceHistoryCount, "Taaj sent: -$" + amount + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void transactionStatus() {

            if (lastTaajRef.equals("")) {
                System.out.println("Ma jiro dhaqdhaqaaq Taaj ah oo dhawaan la sameeyay.");
            } else {
                System.out.println("Xaalada Taaj (" + lastTaajRef + "): GUUL (COMPLETED)");
            }
            backToMenu();
        }


        // 9. BILL PAYMENT


        static final int[] billReferences = {1001, 1002, 1003};
        static double[] billAmountsDue = {12.50, 30.00, 8.75};


        public static void billPaymentMenu() {

            System.out.println("Bill Payment");
            System.out.println("1. Itus Haraaga Bill ka");
            System.out.println("2. Wada bixi Bill-ka");
            System.out.println("3. Qayb ka bixi Bill-ka");
            System.out.println("0. Dib u noqo");

            String option = input.nextLine();

            if (option.equals("1")) {
                checkBillReference();
            } else if (option.equals("2")) {
                payFullBill();
            } else if (option.equals("3")) {
                payPartialBill();
            } else if (option.equals("0")) {
                menu();
            } else {
                System.out.println("Fadlan dooro number sax ah");
                billPaymentMenu();
            }
        }



        static int findBillIndex(int referenceNumber) {
            for (int i = 0; i < billReferences.length; i++) {
                if (billReferences[i] == referenceNumber) {
                    return i;
                }
            }
            return -1;
        }


        public static void checkBillReference() {

            System.out.print("Fadlan gali bill reference number: ");
            int referenceNumber = readInt();

            int index = findBillIndex(referenceNumber);

            if (index == -1) {
                System.out.println("invalid reference");
            } else {
                System.out.println("Bill " + referenceNumber + " haraagiisu waa $" + billAmountsDue[index]);
            }
            backToMenu();
        }


        public static void payFullBill() {

            System.out.print("Fadlan gali bill reference number: ");
            int referenceNumber = readInt();

            int index = findBillIndex(referenceNumber);

            if (index == -1) {
                System.out.println("invalid reference");
                backToMenu();
                return;
            }

            double amountDue = billAmountsDue[index];

            if (amountDue <= 0) {
                System.out.println("Bill-kan " + referenceNumber + " waa horey loo bixiyay.");
                backToMenu();
                return;
            }

            if (confirm("Ma hubtaa inaad wada bixiso bill " + referenceNumber + " oo qiimihiisu yahay $" + amountDue + "?")) {
                if (amountDue > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - amountDue;
                    billAmountsDue[index] = 0;
                    System.out.println("Bill " + referenceNumber + " waa la bixiyay oo dhan. Haraagaagu hadda waa $" + balance);
                    lastAction = "Bill payment (full) " + referenceNumber + " $" + amountDue;
                    addToHistory(balanceHistory, balanceHistoryCount, "Bill " + referenceNumber + " (full): -$" + amountDue + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }


        public static void payPartialBill() {

            System.out.print("Fadlan gali bill reference number: ");
            int referenceNumber = readInt();

            int index = findBillIndex(referenceNumber);

            if (index == -1) {
                System.out.println("invalid reference");
                backToMenu();
                return;
            }

            double amountDue = billAmountsDue[index];

            if (amountDue <= 0) {
                System.out.println("Bill-kan " + referenceNumber + " waa horey loo bixiyay.");
                backToMenu();
                return;
            }

            System.out.println("Haraaga bill " + referenceNumber + " waa $" + amountDue);
            System.out.print("Fadlan geli intaad bixinayso: ");
            double amount = readDouble();

            if (amount > amountDue) {
                System.out.println("Lacagtaas way ka badan tahay haraaga bill-ka.");
                backToMenu();
                return;
            }

            if (confirm("Ma hubtaa inaad qayb ka bixiso bill " + referenceNumber + " $" + amount + "?")) {
                if (amount > balance) {
                    System.out.println("Haraaga xisaabtaadu kuguma filna.");
                } else {
                    balance = balance - amount;
                    billAmountsDue[index] = amountDue - amount;
                    System.out.println("Waxaad qayb ka bixisay bill " + referenceNumber + " $" + amount
                            + ". Haraaga bill-ku hadda waa $" + billAmountsDue[index]
                            + ". Haraaga xisaabtaadu hadda waa $" + balance);
                    lastAction = "Bill payment (partial) " + referenceNumber + " $" + amount;
                    addToHistory(balanceHistory, balanceHistoryCount, "Bill " + referenceNumber + " (partial): -$" + amount + " -> $" + balance);
                    balanceHistoryCount++;
                }
            } else {
                System.out.println("Mahadsanid");
            }
            backToMenu();
        }

    }
