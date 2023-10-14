package com.compressibleflowcalculator.shopping_api.Views;

public class List_View {
    public interface ListPostView {
    }

    public interface ListGetView extends ListPostView {
    }

    public interface ListInternalView extends ListGetView {
    }
}
