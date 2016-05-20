package com.huai.search.version;

/**
 *  The <tt>KMP</tt> class finds the first occurrence of a pattern string
 *  in a text string.
 */
public class KMP {
    private final int R ;       // the radix
    private int[][] dfa;       // the KMP automoton

    private char[] pattern;    // either the character array for the pattern
    private String pat;        // or the pattern string

    public KMP(){
        this.R = 127;
    }

    public KMP(int R){
        this.R = R;
    }

    /**
     * Preprocesses the pattern string.
     *
     * @param pat the pattern string
     */
    private void preparePat(String pat) {
        this.pat = pat;

        // build DFA from pattern
        int M = pat.length();
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];     // Copy mismatch cases.
            dfa[pat.charAt(j)][j] = j+1;   // Set match case.
            X = dfa[pat.charAt(j)][X];     // Update restart state.
        }
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param  txt the text string
     * @return the index of the first occurrence of the pattern string
     *         in the text string; N if no such match
     */
    private int search(String txt) {

        // simulate operation of DFA on text
        int M = pat.length();
        int N = txt.length();
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) return i - M;    // found
        return N;                    // not found
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param txt
     * @param begin
     * @return
     */
    private int search(String txt, int begin){
        if(txt == null) return -1;
        if(begin < 0 || begin >= txt.length())return -1;

        int M = pat.length();
        int N = txt.length();
        int i, j;
        for (i = begin, j = 0; i < N && j < M; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == M) return i - M;    // found
        return N;                    // not found
    }

    public int indexOf(String text, String pattern){
        return indexOf(text, pattern, 0);
    }

    public int indexOf(String text, String pattern, int begin){
        if(text == null || "".equals(text))return -1;
        if(pattern == null || "".equals(pattern)) return -1;

        preparePat(pattern);
        int index = search(text, begin);
        if(index >= text.length()){
            return -1;
        }else{
            return index;
        }
    }



    public static void main(String[] args) {

        String txt = "Hello";
        String pat = "l";

        KMP test = new KMP();
        System.out.println(test.indexOf(txt, pat, 2));

    }
}
