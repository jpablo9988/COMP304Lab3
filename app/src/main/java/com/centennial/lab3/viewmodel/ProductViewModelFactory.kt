import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.centennial.lab3.data.ProductRepository
import com.centennial.lab3.viewmodel.ProductViewModel

class ProductViewModelFactory(private val repository: ProductRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            ProductViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}