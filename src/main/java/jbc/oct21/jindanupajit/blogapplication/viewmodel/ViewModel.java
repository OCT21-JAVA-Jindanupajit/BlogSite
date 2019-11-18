package jbc.oct21.jindanupajit.blogapplication.viewmodel;

public class ViewModel<T> {
    private String view;
    private T viewModel;

    public ViewModel() {
        this.view = "";
        this.viewModel = null;

    }

    public ViewModel(String view, T viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public T getViewModel() {
        return viewModel;
    }

    public void setViewModel(T viewModel) {
        this.viewModel = viewModel;
    }
}
