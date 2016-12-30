package com.example.malmad.basic;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    private Clients clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clients = new Clients(this);
        new CreateButton();
        new DeleteButton();
    }

    public class CreateButton implements View.OnClickListener {

        private Button mySelf;

        public CreateButton() {
            mySelf = (Button)findViewById(R.id.Create);
            mySelf.setOnClickListener(this);
        }

        public void onClick(View v) {
            EditText newClient = (EditText)findViewById(R.id.NewClient);
            String client = newClient.getText().toString();

            clients.Add(client);
            newClient.setText("");
        }
    }

    public class DeleteButton implements View.OnClickListener {

        private Button mySelf;

        public DeleteButton() {
            mySelf = (Button)findViewById(R.id.Delete);
            mySelf.setOnClickListener(this);
        }

        public void onClick(View v) {
            clients.RemoveSelected();
        }
    }

    public class Clients {

        private LinkedHashMap clients;
        private Spinner mySelf;
        private Activity parent;

        public Clients(Activity parent) {
            mySelf = (Spinner)findViewById(R.id.Clients);
            this.parent = parent;

            InitData();
            UpdateData();
        }

        public void Add(String name) {
            int position = clients.size();
            clients.put(position, name);
            UpdateData();
        }

        private void RemoveSelected() {
            int position = mySelf.getSelectedItemPosition();
            clients.remove(position);
            UpdateData();
        }

        private void InitData(){
            clients = new LinkedHashMap<Integer, String>();
            clients.put(0, "Android");
            clients.put(1, "IOS");
            clients.put(2, "Windows");
            clients.put(3, "Linux");
        }

        private void UpdateData() {
            ArrayList<String> data = new ArrayList<String>(clients.values());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(parent, android.R.layout.simple_spinner_item, data);
            mySelf.setAdapter(adapter);
        }
    }
}
