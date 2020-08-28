package com.sample.assignment.datatest

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sample.assignment.db.data.MainList
import com.sample.assignment.db.data.Row
import com.sample.assignment.repository.Repository
import com.sample.assignment.util.ApiResponse
import com.sample.assignment.util.CoroutineRule
import com.sample.assignment.viewmodel.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

/**
Created by Ajay Arya on 28/08/20
 */

@ExperimentalCoroutinesApi
class ListTest {
    @get: Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineRule()

    @Mock
    private lateinit var context: Application

    @Mock
    private lateinit var listRepository: Repository

    @InjectMocks
    lateinit var listViewModel: ListViewModel

    private lateinit var mainList: MainList
    private lateinit var onRenderListObserver: Observer<MainList>
    private lateinit var onRenderErrorListObserver: Observer<String>

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        `when`<Context>(context.applicationContext).thenReturn(context)

        mockResponse()
        setupObservers()
    }

    @Test
    fun getListFromRepository() {

        testCoroutineRule.runBlockingTest {
            `when`(listRepository.getList()).thenReturn(
                ApiResponse.Success(
                    mainList
                )
            )
            with(listViewModel) {
                getList()
                listViewModel.mainList.observeForever(onRenderListObserver)
            }

            delay(1000)

            verify(onRenderListObserver).onChanged(listViewModel.mainList.value)

            assertEquals(
                listViewModel.mainList.value,
                mainList
            )
        }
    }

    private fun setupObservers() {
        onRenderListObserver = mock(Observer::class.java) as Observer<MainList>
        onRenderErrorListObserver = mock(Observer::class.java) as Observer<String>
    }

    private fun mockResponse() {
        val tempList =
            MainList(
                listOf(
                    Row(
                        "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
                        "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg",
                        "Beavers"
                    ), Row(
                        "It is a well known fact that polar bears are the main mode of transportation in Canada. They consume far less gas and have the added benefit of being difficult to steal.",
                        "http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg",
                        "Transportation"
                    )
                ),
                "About Canada"
            )

        mainList = tempList
    }
}
