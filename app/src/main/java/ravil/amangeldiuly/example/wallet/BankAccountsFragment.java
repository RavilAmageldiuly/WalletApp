package ravil.amangeldiuly.example.wallet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Objects;

public class BankAccountsFragment extends Fragment {

    View mView;
    ImageButton addAccountButton;
    LinearLayout bankAccountHolder;
    Spinner bankAccountTypeSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_bank_account, container, false);
        addAccountButton = mView.findViewById(R.id.add_card_button);
        bankAccountHolder = mView.findViewById(R.id.bank_accounts_holder);
        addAccountButton.setOnClickListener(buttonClickListener);

        return mView;
    }

    private Button.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            View bankAccountsView = getLayoutInflater().inflate(R.layout.bank_accounts, null, false);
//            bankAccountHolder.addView(bankAccountsView);

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
            View bottomSheetView = LayoutInflater.from(getContext())
                    .inflate(R.layout.bottom_sheet_layout, mView.findViewById(R.id.bottomSheetContainer));
            bottomSheetView.findViewById(R.id.add_bank_account_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bottomSheetDialog.dismiss();
                }
            });
            bankAccountTypeSpinner = bottomSheetView.findViewById(R.id.bank_account_type_spinner);
            bankAccountTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i) {
                        case 0:
                            bottomSheetView.findViewById(R.id.bottom_sheet_charge_edit_t).setVisibility(View.GONE);
                            bottomSheetView.findViewById(R.id.bottom_sheet_percent_edit_t).setVisibility(View.GONE);
                            break;
                        case 1:
                            bottomSheetView.findViewById(R.id.bottom_sheet_percent_edit_t).setVisibility(View.GONE);
                            bottomSheetView.findViewById(R.id.bottom_sheet_charge_edit_t).setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            bottomSheetView.findViewById(R.id.bottom_sheet_charge_edit_t).setVisibility(View.GONE);
                            bottomSheetView.findViewById(R.id.bottom_sheet_percent_edit_t).setVisibility(View.VISIBLE);
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        }
    };
}