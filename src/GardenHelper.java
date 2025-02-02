public class GardenHelper {
    private final GardenView view = new GardenView();

    GardenPlant daisy, carrot, rose, swissChard, begonia;
    GardenRow one, two, three;

    public void go() {
        view.printMenu();
        String action = view.getAction();
        while(!action.startsWith("n")) {
            if(action.startsWith("a")) {
                int row = view.getRow();
                int pH = Integer.parseInt(view.input("What is the pH of the row? "));
                String light = view.input("What is the sun coverage of the row? (full sun, partial sun, any) ");
                addRow(row, pH, light);
            }
            else if(action.startsWith("p")) {
                String plantName = view.input("What plant would you like to plant? (daisy, carrot, rose, swiss chard, begonia): ");
                int row = view.getRow();
                addPlant(plantName, row);
            }
            System.out.println("What would you like to do? ");
            action = view.getAction();
        }
    }
    /*
       BRAINSTORM FOR ADDROW() HERE
       check to make sure the row is valid
       set the pH and light values for that row, return true
       else return false
     */
    public boolean addRow(int row, int pH, String light) {
        if(row == 1){
            one = new GardenRow(light, pH);
            return true;
        }
        if(row == 2){
            two = new GardenRow(light, pH);
            return true;
        }
        if(row == 3){
            three = new GardenRow(light, pH);
            return true;
        }

        return false;
    }
    /*
        DESCRIPTION OF ADDPLANT() HERE
        checks to see if canPlant returns true
        sets the row value to store the plant values
        prints to console
     */
    public boolean addPlant(String plantName, int row) {
        if(canPlant(plantName, searchRow(row))){
            searchRow(row).setPlant(searchPlant(plantName));
            System.out.println(plantName.toUpperCase() + " added to row " + row + "!");
            return true;
        }
        else{
            System.out.println(plantName.toUpperCase() + " was unable to be planted.");
            return false;
        }
    }

    //STEP 3
    public boolean canPlant(String plantName, GardenRow row){
        if(searchPlant(plantName).getAmountOfSunshine().equals(row.getLight())){           /* amount oof sunshine compared to light */
            if(searchPlant(plantName).getpHLow() <= row.getPH() && searchPlant(plantName).getpHHigh() >= row.getPH()){               /* lowPh <= object pH && high pH >= object Ph */
                if((one != null && one.getPlant() != null && one.getPlant().getType().equals("VEG")) || (two != null && two.getPlant() != null && two.getPlant().getType().equals("VEG")) || (three != null && three.getPlant() != null && three.getPlant().getType().equals("VEG"))){
                    return true;
                }
                if(searchPlant(plantName).getType().equals("VEG")){
                    return true;
                }
            }
        }
        return false;
    }


    public void buildOptions() {
        daisy = new GardenPlant("daisy", 6, 8, "any", "FLOWER");
        carrot = new GardenPlant("carrot", 6, 7, "full sun", "VEG");
        rose = new GardenPlant("rose", 6, 7, "full sun", "FLOWER");
        swissChard = new GardenPlant("swiss chard", 6, 7, "partial sun", "VEG");
        begonia = new GardenPlant("begonia", 5, 6, "partial sun", "FLOWER");
    }


    public GardenPlant searchPlant(String plantName){
        if(plantName.equals(daisy.getName())){
            return daisy;
        }
        else if(plantName.equals(carrot.getName())){
            return carrot;
        }
        else if(plantName.equals(rose.getName())){
            return rose;
        }
        else if(plantName.equals(swissChard.getName())){
            return swissChard;
        }
        else{
            return begonia;
        }
    }


    public GardenRow searchRow(int row){
        if(row == 1){
            return one;
        }
        else if(row == 2){
            return two;
        }
        else{
            return three;
        }

    }


    public static void main(String[] args) {
        GardenHelper gardenHelperObj = new GardenHelper();
        gardenHelperObj.buildOptions();
        //Test 1 for addRow():
        System.out.println("Testing addRow(4, 6, any):");
        System.out.println("EXPECTING: false");
        System.out.println("ACTUAL   : " + gardenHelperObj.addRow(4, 6, "any"));
        System.out.println();

        //Test 2 for addRow():
        System.out.println("Testing addRow(2, 6, any):");
        System.out.println("EXPECTING: true");
        System.out.println("ACTUAL   : " + gardenHelperObj.addRow(2, 6, "any"));
        System.out.println();

        //Test 1 for canPlant():
        gardenHelperObj.addRow(2, 6, "full sun");
        System.out.println("Testing canPlant(carrot, 2):");
        System.out.println("EXPECTING: true");
        System.out.println("ACTUAL   : " + gardenHelperObj.addPlant("carrot", 2));
        System.out.println();

        //Test 2 for canPlant():
        gardenHelperObj.addRow(1, 2, "full sun");
        System.out.println("Testing canPlant(carrot, 2):");
        System.out.println("EXPECTING: false");
        System.out.println("ACTUAL   : " + gardenHelperObj.addPlant("carrot", 1));
        System.out.println();

    }

}
