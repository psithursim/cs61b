public class WordUtils {
    public static String Findlongest(SLList<String> list) {
        list.first= list.first.next;
        int pos = 0;
        String max = "";
        while (pos != list.size) {
            if (list.first.item.length() > max.length()) {
                max = list.first.item;
            }
            list.first= list.first.next;
            pos++;
        }
        return max;
    }

    public static void main(String[] args) {
        SLList<String> list = new SLList<>();
        list.addLast("I");
        list.addLast("have");
        list.addLast("a");
        list.addLast("story");
        String longest = Findlongest(list);
        System.out.println(longest);
    }
}
