package com.programmer.arvy.challenge3
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.programmer.challenge3.R

class FragmentMenu<MenuItem : Any> : Fragment() {
    private var fragmentMenuListener: FragmentMenuListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu2, container, false)

        // Inisialisasi RecyclerView
        val recyclerViewMenuGrid: RecyclerView = view.findViewById(R.id.recyclerViewMenuGrid)

        // Inisialisasi data menu makanan Anda
        val menuItems = mutableListOf<MenuItem>()

        menuItems.add(MenuItem(
            "Kentang Goreng",
            "Rp 20.000",
            "Kentang Goreng pakai saus tomat",
            R.drawable.img_2,
            "Alamat Restoran 1",
            "https://maps.app.goo.gl/Kd1hbopN2DhnY4DQ9"
        ))

        menuItems.add(MenuItem(
            "Chicken",
            "Rp 20.000",
            "Chicken dengan sambal ijo",
            R.drawable.img_3,
            "Alamat Restoran 2",
            "https://maps.app.goo.gl/Kd1hbopN2DhnY4DQ9"
        ))

        menuItems.add(MenuItem(
            "Sushi",
            "Rp 35.000",
            "Sushi makanan favorit anak jaman now",
            R.drawable.img_5,
            "Alamat Restoran 3",
            "https://maps.app.goo.gl/Kd1hbopN2DhnY4DQ9"
        ))

        menuItems.add(MenuItem(
            "Dimsum",
            "Rp 25.000",
            "Dimsum makanan paling dicari semua orang",
            R.drawable.img,
            "Alamat Restoran 4",
            "https://maps.app.goo.gl/Kd1hbopN2DhnY4DQ9"
        ))

        menuItems.add(MenuItem(
            "Ayam Bakar",
            "Rp 50.000",
            "Dengan ayam pilihan menciptakan kenikmatan yang sempurna",
            R.drawable.img_6,
            "Alamat Restoran 1",
            "https://maps.app.goo.gl/Kd1hbopN2DhnY4DQ9"
        ))

        // Buat adapter untuk RecyclerView
        val adapter = MenuAdapter(menuItems) { menuItem ->

            // Ketika item di RecyclerView diklik, buat Bundle untuk mengirim data ke FragmentDetail
            val args = Bundle()
            args.putString("name", menuItem.name)
            args.putString("price", menuItem.price)
            args.putString("description", menuItem.description)
            args.putInt("imageRes", menuItem.imageRes)
            args.putString("restaurantAddress", menuItem.restaurantAddress)
            args.putString("googleMapsUrl", menuItem.googleMapsUrl)

            // Mengirim permintaan untuk menampilkan FragmentDetail
            fragmentMenuListener?.onMenuItemClicked(args)
        }

        // Atur layout manager untuk RecyclerView
        val layoutManager = GridLayoutManager(requireContext(), 2) // 2 kolom
        recyclerViewMenuGrid.layoutManager = layoutManager

        // Set adapter ke RecyclerView
        recyclerViewMenuGrid.adapter = adapter

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentMenuListener) {
            fragmentMenuListener = context
        } else {
            throw RuntimeException("$context must implement FragmentMenuListener")
        }
    }

    interface FragmentMenuListener {
        fun onMenuItemClicked(args: Bundle)
    }
}

private fun <E> MutableList<E>.add(element: MenuItem) {

}
