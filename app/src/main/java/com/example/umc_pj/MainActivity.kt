package com.example.umc_pj;
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.umc_pj.databinding.ActivityMainBinding
import com.example.umc_pj.homepackage.NaviHomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private val fl: FrameLayout by lazy {
        findViewById(R.id.main_frm)
    }

    private var backPressedTime : Long = 0
    // 액션버튼 메뉴 액션바에 집어넣기

    // 이 부분은 뒤로가기 이벤트 처리용 코드. 후에 사용 할듯
//    interface onBackPressedListener {
//        fun onBackPressed()
//    }
//    override fun onBackPressed(){
//        Log.d("sad","dsad")
//        // 해당 엑티비티에서 띄운 프래그먼트에서 뒤로가기를 누르게 되면 프래그먼트에서 구현한 onBackPressed 함수가 실행되게 된다.
//        val fragmentList = supportFragmentManager.fragments
//        // 플래그먼트에서 뒤로가기 구현
//        for (fragment in fragmentList) {
//            if (fragment is onBackPressedListener) {
//                (fragment as onBackPressedListener).onBackPressed()
//                return
//            }else{
//                // 네비게이션에 있는 메이들로 가면 꺼짐
//                finish()
//            }
//        }
//    }

    // 액션버튼 클릭 했을 때
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item?.itemId){
//            R.id.action_notice -> {
//                // 알림 버튼 눌렀을 때
//                Toast.makeText(applicationContext, "알림 이벤트 실행", Toast.LENGTH_LONG).show()
//                supportFragmentManager.beginTransaction().replace(R.id.main_frm, NoticeFragment()).commit()
//                var cv = findViewById<Toolbar>(R.id.toolbar2) = View.VISIBLE
//
//                return super.onOptionsItemSelected(item)
//            }
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(NaviCommunityFragment())

        val main_bnv = findViewById<BottomNavigationView>(R.id.main_bnv)
        setSupportActionBar(toolbar) // 커스텀한 toolbar를 액션바로 사용
        supportActionBar?.setDisplayShowTitleEnabled(false) // 액션바에 표시되는 제목의 표시유무를 설정합니다. false로 해야 custom한 툴바의 이름이 화면에 보이게 됩니다.

        var noticeitem = findViewById<ImageView>(R.id.noticeitem)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var toolbar2 = findViewById<Toolbar>(R.id.toolbar2)

        close_notice.setOnClickListener {
            val transaction = supportFragmentManager.popBackStack()
            toolbar.visibility = View.VISIBLE
            toolbar2.visibility = View.INVISIBLE

        }

        noticeitem.setOnClickListener{
            val transaction = supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, NoticeFragment())
                .addToBackStack(null)
            transaction.commit()
            toolbar.visibility = View.INVISIBLE
            toolbar2.visibility = View.VISIBLE

        }

        main_bnv.setOnItemSelectedListener { item ->
            changeFragment(
                when (item.itemId) {
                    R.id.navigation_home -> {
                        main_bnv.itemIconTintList = null
                        NaviHomeFragment()
                        // Respond to navigation item 1 click
                    }
                    R.id.navigation_community -> {
                        main_bnv.itemIconTintList = null
                        NaviCommunityFragment()
                        // Respond to navigation item 2 click
                    }
                    R.id.navigation_calendar -> {
                        main_bnv.itemIconTintList = null
                        Calendar_fragment()
                    }
                    else -> {
                        main_bnv.itemIconTintList = null
                        NaviMypageFragment()
                    }
                }
            )
            true
        }
        main_bnv.selectedItemId = R.id.navigation_home
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.popBackStackImmediate()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frm, fragment)
            .commit()
    }

    private fun replaceFragment(naviCommunityFragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frm, naviCommunityFragment)
        fragmentTransaction.commit()
    }
}