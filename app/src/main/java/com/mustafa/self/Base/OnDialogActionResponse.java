package com.mustafa.self.Base;

public interface OnDialogActionResponse<T> {

    void onPositiveButton(T response);

    void onNegativeButton();
}
