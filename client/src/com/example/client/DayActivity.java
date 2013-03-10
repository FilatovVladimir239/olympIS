package com.example.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/* Класс показывающий рассписание конкретного дня */
public class DayActivity extends Activity implements OnClickListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_view);

		 findViewById(R.id.dayViewCloseButton).setOnClickListener(this);

		//устанавливаем №дня
		((TextView) findViewById(R.id.dayNumberTextView)).setText("day №" + this.getIntent().getStringExtra("dayNumber"));

        // нужен ещё метод заполнения рассписанием формочки
    }

	public void onClick(View view){
		switch (view.getId()){
			case R.id.dayViewCloseButton:
				this.finish();
				break;
		}
	}
}