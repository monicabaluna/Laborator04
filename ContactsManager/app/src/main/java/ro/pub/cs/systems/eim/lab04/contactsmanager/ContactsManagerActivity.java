package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {
    private Button showMoreButton;
    private Button saveButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        showMoreButton = findViewById(R.id.show_more_button);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);

        showMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout detailsContainer = findViewById(R.id.details_container);
                if (detailsContainer.getVisibility() == View.VISIBLE) {
                    showMoreButton.setText("Show Additional Fields");
                    detailsContainer.setVisibility(View.GONE);
                }
                else {
                    detailsContainer.setVisibility(View.VISIBLE);
                    showMoreButton.setText("Hide Additional Fields");
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.name_edit_text)).getText().toString();
                String phone = ((EditText) findViewById(R.id.phone_edit_text)).getText().toString();
                String email = ((EditText) findViewById(R.id.email_edit_text)).getText().toString();
                String address = ((EditText) findViewById(R.id.postal_address_edit_text)).getText().toString();
                String jobTitle = ((EditText) findViewById(R.id.position_edit_text)).getText().toString();
                String company = ((EditText) findViewById(R.id.company_name_edit_text)).getText().toString();
                String website = ((EditText) findViewById(R.id.web_addr_edit_text)).getText().toString();
                String im = ((EditText) findViewById(R.id.mess_id_edit_text)).getText().toString();

                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                if (name != null)
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);

                if (phone != null)
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);

                if (email != null)
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);

                if (address != null)
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);

                if (jobTitle != null)
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);

                if (company != null)
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);

                ArrayList<ContentValues> contactData = new ArrayList<>();
                if (website != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                    contactData.add(websiteRow);
                }
                if (im != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
