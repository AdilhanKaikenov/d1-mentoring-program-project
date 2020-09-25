package com.epam.mentoring.memory.task1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kaikenov Adilhan
 **/
public class SubTask5 {

//        5. java.lang.StackOverflowError. Do not use recursive methods. Don’t tune stack size.
    public static void main(String[] args) {
        Chain chain = new Chain();
        chain.setFilters(10000);
        chain.process();
    }

    static class Filter {
        void doFilter(Chain chain){
            chain.process();
        }
    }

    static class Chain {
        private List<Filter> filters = new ArrayList<>();
        private int n = 0;

        private void setFilters(final int filterNum) {
            int i = 0;
            while (i++ < filterNum) {
                this.filters.add(new Filter());
            }
        }

        void process() {
            if (this.n < this.filters.size()) {
                Filter filter = this.filters.get(this.n++);
                filter.doFilter(this);
            }
        }
    }
}
