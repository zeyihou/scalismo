package smptk.image

import breeze.plot._
import breeze.linalg._
import scala.util._
import java.io.IOException


object Interpolation {
 

  def splineInterpolate[Point](p: Point): (Point => Float) = p => 0
    
  def cubicSpline(x:Float) : Float = {
	val absX = scala.math.abs(x)
	
    if(absX>=0 && absX<1)
		(0.66666666-scala.math.pow(absX, 2)+scala.math.pow(absX, 3)).toFloat
	else if (absX >= 1 && absX < 2)
		(scala.math.pow((2-absX),3)/6).toFloat
	else 0
  
  }
  
  def interpolationScalar1D(image:DiscreteScalarImage1D) : ContinuousScalarImage1D ={
    val ck = DenseVector.ones[Float](image.domain.size(0))
    
    new ContinuousScalarImage1D( 
    		ContinuousImageDomain1D(image.domain.origin, image.domain.extent), //new domain
    		(x:CoordVector1D) => (1f), 
    		
    		(x:CoordVector1D) => DenseVector(1f) ) 
    	
  }
  
  
  def interpolationScalar2D(image:DiscreteScalarImage2D):ContinuousScalarImage2D = {
   new ContinuousScalarImage2D(
		   ContinuousImageDomain2D(image.domain.origin, image.domain.extent), //new domain
    		(x:CoordVector2D) => (1f), 
    		
    		(x:CoordVector2D) => DenseVector(1f) ) 
 
  }
  
  
//  /* Think we don't need this, can directly call the right function , or ?*/
//  def scalarInterpolation[CoordVector <: CoordVectorLike](image: DiscreteImageLike[CoordVector, Float]): ContinuousScalarImageLike[CoordVector] = {
//
//    image.domain.dimensionality match {
//      case 	1 =>  interpolationScalar1D(image.asInstanceOf[DiscreteScalarImage1D])
//      case _=> interpolation2D(image) 
//    
//    } 
// 	
//  }
    
  
  
  def main(args: Array[String]) {
	  val a : Try[Int]= Failure(new IOException("abc"))
	  val b : Try[Int]= Success(5)
    val f = Figure()
    val p = f.subplot(0)
    val x = linspace(0.0, 1.0)
    println("here: " +a.getOrElse("0"))
    
    val c = for {
      value <- a
      value2 <- b
    }
      yield(value + value2)
    
    p += plot(x, x :^ 2.0)
    p += plot(x, x :^ 3.0, '.')
    p.xlabel = "x axis"
    p.ylabel = "y axis"
    f.saveas("lines.png") // save current figure as a .png, eps and pdf also supported
    println("hello world")
  }
}



//def apply(x:Space#Point) : Float = {
//    	   val xUnit = (spaceOperations.getComponentPoint(x,0) - spaceOperations.getComponentPoint(image.domain.origin,0)) / spaceOperations.getComponentVector(image.domain.spacing,0)
//    	  	val k = scala.math.ceil(xUnit - 2).toInt 
//    	  	val K = 4  
//    	  	   	 
//    	  	
//    		var result = 0f	
//    		var i = k 
//    		while(i <= K){
//    		  result = result + cubicSpline(spaceOperations.getComponentPoint(x,0)-k)*ck(k)
//    		  i=i+1
//    		}
//    	   result
//    	}